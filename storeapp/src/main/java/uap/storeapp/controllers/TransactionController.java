package uap.storeapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uap.storeapp.models.*;
import uap.storeapp.repos.TransactionRepo;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    TransactionRepo transactionRepo;

    @GetMapping("/test")
    void test() {
        System.out.println("Hello World, Im here");
    }

    @PostMapping("/record")
    public Receipt record(@RequestBody Transaction transaction) {
        System.out.println(transaction.toString());

        //save transaction info
        List<Product> products = transaction.getProducts();
        transactionRepo.saveTransaction(transaction.getCustomerName(), transaction.getCustomerAddress());
        //get transaction id;
        int id = transactionRepo.getLastId();

        //save products into transaction_items table;
        double total = 0;
        for (Product product : transaction.getProducts()) {
            transactionRepo.insertProd(id, product.getBarcode(), product.getUnitPrice(), product.getQuantity());
            Double temp = product.getUnitPrice() * product.getQuantity();
            System.out.println("adding: " + temp);
            total += temp;
        }

        Receipt receipt = new Receipt(id, total);
        System.out.println("Printing receipt");
        System.out.println(receipt.toString());

        return receipt;
    }

    @GetMapping("/search/{id}")
    SearchReturn search(@PathVariable int id) {
        System.out.println("Got : " + id);
        SearchReturn searchReturn = new SearchReturn();

            Transaction transaction = transactionRepo.search(id);
            System.out.println(transaction.toString());

            //get list of products purchased
            System.out.println("getting products with: " + transaction.getTransactionId());
            List<SearchDTO> products = transactionRepo.searchTrans(transaction.getTransactionId());
            Double total = 0.0;

            for (SearchDTO dto : products) {
                Double temp = dto.getUnitPrice() * dto.getQuantity();
                total += temp;
            }

            //lets build the return
            searchReturn.setName(transaction.getCustomerName());
            searchReturn.setAddress(transaction.getCustomerAddress());
            searchReturn.setProducts(products);
            searchReturn.setTotal(total);

            return searchReturn;



    }

}
