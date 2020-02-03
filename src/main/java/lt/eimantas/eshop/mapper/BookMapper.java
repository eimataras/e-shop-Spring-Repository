package lt.eimantas.eshop.mapper;

import lt.eimantas.eshop.model.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper {

    @Select("select * from book")
    List<Book> findAll();

}
