import com.java_study.mybatis.mapper.RoleMapper;
import com.java_study.mybatis.mapper.system.EmployeeMapper;
import com.java_study.mybatis.model.Role;
import com.java_study.mybatis.model.system.Employee;
import jdk.internal.instrumentation.Logger;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 级联测试函数
 */
public class CascadeTest {
    private  static SqlSessionFactory sqlSessionFactory;

    public static void main(String[] args){
        //mybatis配置文件
        String resource = "mybatis.cfg.xml";
        //得到配置源
        InputStream inputStream=null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        }catch (IOException e){
            e.printStackTrace();
        }
        // 创建会话工厂，传入 MyBatis 的配置文件信息
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        getEmployee();

    }
    // 根据 id 查询雇员信息
    private static void getEmployee() {

        SqlSession session = sqlSessionFactory.openSession();

        EmployeeMapper mapper=session.getMapper(EmployeeMapper.class);
        try {
            Employee employee=mapper.getEmployee(1L);
            session.commit();
            System.out.println(employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }

        session.close();
    }
}
