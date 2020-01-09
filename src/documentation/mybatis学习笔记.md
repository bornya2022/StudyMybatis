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

