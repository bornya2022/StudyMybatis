# mybatis学习笔记

## 一.映射器模块

### 1.1 级联

**级联操作的实现**：

Mybatis中的级联分为3个种：

鉴别器（discriminator）：根据某些特定条件决定采用具体实现类级联。

```xml
<!--鉴别器-->
        <discriminator javaType="long" column="sex">
            <case value="1" resultMap="maleHealthFormMapper"/>
            <case value="2" resultMap="femaleHealthFormMapper"/>
        </discriminator>
```

一对一级联（association）：对应数据库的一对一关系

```xml
<association property="workCard" column="id"                     select="com.java_study.mybatis.mapper.system.WorkCardMapper.getWorkCardByEmpId"/>
```

一对多级联（collection）:对应数据库一对多关系

```xml
<collection property="employeeTaskList" column="id"           select="com.java_study.mybatis.mapper.system.EmployeeTaskMapper.getEmployeeTaskByEmpId"/>
```

Mybatis不支持多对多关系，可以使用2个一对多实现多对多。

**具体实现操作：**

数据库：采用外键建立级联关系

POJO：将一个类作为另外一个类的元素；

```java
    private String note;
    //工牌：一对一级联
    private WorkCard workCard;
    //雇员任务：一对多级联
    private List<EmployeeTask> employeeTaskList=null;
```

xml：采用association元素实现一对一，collection元素实现一对多

**简单级联的问题**：N+1问题

简单级联会导致执行较多无用SQL，加载无用信息，性能下降。

问题解决：采用延迟加载策略。
简单实现：

```xml
 <!--lazyLoadingEnabled元素：延迟加载的全局开关，默认值为flase-->
        <setting name="lazyLoadingEnabled" value="true"/>
        <!--aggressiveLazyLoading元素：层级加载开关，同一层级一起加载，默认值为flase-->
        <setting name="aggressiveLazyLoading" value="true"/>
```

### 1.2缓存

Mybatis的缓存分为2种，分别是一级缓存和二级缓存。

**一级缓存实在SqlSession上的缓存，二级缓存是在SqlSessionFactory上的缓存。**

- 默认情况下，系统自动开启一级缓存。(一级缓存不需要POJO对象序列化)
- 一级缓存是SqlSession层面，不同SqlSession之间不能共享缓存。
- 二级缓存能够使不同SqlSession之间共享缓存。

二级缓存开启：

首先：在xml文件中：配置

```xml
<cache/>
只配置cache元素，该xml文件所有SQL都会影响缓存（select元素的查询结果进行缓存，insert，delete，update元素操作结果更新缓存）
```

接着：对应的POJO需要实现Serializable接口（要求对应的POJO是一个可序列化对象）。

测试结果：

```
Cache Hit Ratio [com.java_study.mybatis.mapper.RoleMapper]: 0.0
```

### 1.3动态SQL

![img](https://images2018.cnblogs.com/blog/1001990/201804/1001990-20180420091927414-873899959.png)

##### 动态SQL的元素

| 元素                    | 作用                         | 备注                    |
| ----------------------- | ---------------------------- | ----------------------- |
| if                      | 判断语句                     | 单条件分支判断          |
| choose、when、otherwise | 相当于Java中的 case when语句 | 多条件分支判断          |
| trim、where、set        | 辅助元素                     | 用于处理一些SQL拼装问题 |
| foreach                 | 循环语句                     | 在in语句等列举条件常用  |

对应实例：

```xml
<!--动态SQL之if和test标签的应用（单条件分支），实现简单的参数判空，减少多余数据-->
    <select id="findRoles01" parameterType="string" resultType="roleMap">
        select id role_name,as roleName,note from t_role where 1=1
        <if test="roleName!=null and roleName!=''">
            and  role_name like concat('%',#{roleName},'%')
        </if>
    </select>
    <!--动态SQL之choose，when，otherwise标签的应用（多条件分支）-->
    <select id="findRoles02" parameterType="string" resultType="roleMap">
        select id role_name,as roleName,note from t_role where 1=1
        <choose>
            <when test="roleNo!=null and roleNo!=''">
                and role_no=#{roleNo}
            </when>
            <when test="roleName!=null and roleName!=''">
                and role_name like concat('%',#{roleName},'%')
            </when>
            <otherwise>
                and note is not null
            </otherwise>
        </choose>
    </select>
    <!--动态SQL之where标签的应用（辅助元素），where内条件成立，拼接内部的动态SQL-->
    <select id="findRoles03" parameterType="string" resultType="roleMap">
        select id role_name,as roleName,note from t_role
        <where>
            <if test="roleName!=null and roleName!=''">
                and  role_name like concat('%',#{roleName},'%')
            </if>
        </where>
    </select>
    <!--动态SQL之trim标签的应用（辅助元素），去掉特殊的SQL语法，列如and，or等-->
    <!--prefix元素语句的前缀，prefixOverrides元素代表需要去掉的字符串-->
    <select id="findRoles04" parameterType="string" resultType="roleMap">
        select id role_name,as roleName,note from t_role
        <trim prefix="where" prefixOverrides="and">
            <if test="roleName!=null and roleName!=''">
                and  role_name like concat('%',#{roleName},'%')
            </if>
        </trim>
    </select>
<!--动态SQl之set标签应用，对于联合主键，或者多个字段主键的数据更新，避免使用多条SQL进行更新-->
    <update id="updateRole01" parameterType="role">
        update t_role
        <set>
            <if test="roleName!=null and roleName!=''">
                role_name=#{roleName}
            </if>
            <if test="note!=null and note!=''">
                note=#{note}
            </if>
        </set>
        where role_no=#{roleNo}
    </update>
    <!--动态SQL之foreach标签的应用（循环遍历），能够很好的支持数组，list，set接口的几个，并且对此提供遍历功能。-->
    <!--foreac常用于in关键字（多值查询，包含查询，注意：对于大量数据，in会消耗大量性能）-->
    <select id="findUserBySex" resultType="role">
        select * from t_role where role_no in
        <foreach collection="roleNoList" item="roleNo" index="index" open="(" separator="," close=")">
            #{roleNo}
        </foreach>
    </select>
```

