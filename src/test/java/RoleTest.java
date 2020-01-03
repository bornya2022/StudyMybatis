import com.java_study.mybatis.mapper.RoleMapper;
import com.java_study.mybatis.mapper.UserMapper;
import com.java_study.mybatis.model.Role;
import com.java_study.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RoleTest {
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

        //insertUser();
        // updateUser();
        // deleteUser();
        selectRoleId();
        selectRowBounds();

    }
    // 根据 id 查询用户信息
    private static void selectRoleId() {

        SqlSession session = sqlSessionFactory.openSession();

        RoleMapper mapper = session.getMapper(RoleMapper.class);
        try {
            Role role=mapper.getRole(1L);
            session.commit();
            System.out.println(role.getId()+role.getRoleName()+role.getNote());
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }

        session.close();
    }

    /**
     * 分页查询函数，调用RowBounds.
     */
    private  static void selectRowBounds(){
        SqlSession session=sqlSessionFactory.openSession();
        RoleMapper mapper=session.getMapper(RoleMapper.class);
        try {
            //提供2个重要参数：offset(偏移量)和limit(限制条数)
            RowBounds rowBounds=new RowBounds(0,20);
            List<Role> roleList=mapper.findByRowBounds("role_name","医生",rowBounds);
            System.err.println(roleList.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session!=null){
                session.close();
            }
        }
    }
}
