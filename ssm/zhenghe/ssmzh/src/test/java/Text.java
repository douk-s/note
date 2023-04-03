import com.douk.dao.BookMapper;
import com.douk.pojo.Books;
import com.douk.service.BookService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Text {
    @Test
    public void s() throws IOException {
        InputStream is = Resources.getResourceAsStream("BookMapper.xml");
        SqlSessionFactoryBuilder s=new SqlSessionFactoryBuilder();
        SqlSessionFactory s2=s.build(is);
        SqlSession sqlSession = s2.openSession(true);
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        List<Books> books = mapper.queryAllBooks();
        books.forEach(System.out::println);
    }

    @Test
    public void s1() throws IOException {
        ApplicationContext c = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookServiceImpl = (BookService) c.getBean("BookServiceImpl");
        for (Books queryAllBook : bookServiceImpl.queryAllBooks()) {
            System.out.println(queryAllBook);
        }


    }
}
