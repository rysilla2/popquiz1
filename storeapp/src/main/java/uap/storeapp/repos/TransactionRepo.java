package uap.storeapp.repos;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import uap.storeapp.models.SearchDTO;
import uap.storeapp.models.SearchReturn;
import uap.storeapp.models.Transaction;

import java.util.List;

@Mapper
public interface TransactionRepo {


    @Insert("insert into transactions(customerName, customerAddress) values (#{customerName}, #{customerAddress})")
    public void saveTransaction(String customerName, String customerAddress);

    @Select("select LAST_INSERT_ID()")
    public int getLastId();

    @Insert("insert into transaction_items(transactionId, barcode, unitPrice, quantity) values (#{transactionId},#{barcode}, #{unitPrice}, #{quantity})")
    public int insertProd(int transactionId, String barcode, double unitPrice, int quantity);

    @Select("Select * from transactions where transactionId = #{id}")
    public Transaction search(int id);

    @Select("Select * from transaction_items where transactionId = #{id}")
    public List<SearchDTO> searchTrans(int id);

}
