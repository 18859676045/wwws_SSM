import com.www.dao.IProductDao;
import com.www.main.Product;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class daotest {


    /**
     * 查找方法
     */
    @Test
    public void findAll(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        IProductDao iProductDao = ac.getBean(IProductDao.class);
        List<Product> productDaoAll = iProductDao.findAll();

        System.out.println(productDaoAll);
    }
}
