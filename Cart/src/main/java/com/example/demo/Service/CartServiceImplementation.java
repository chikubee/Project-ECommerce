package com.example.demo.Service;

import com.example.demo.DbObject;
import com.example.demo.Dto.ProductDto;
import com.example.demo.Product;
import com.example.demo.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImplementation implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public boolean addToCart(ProductDto productDto) {

//        int key = Integer.parseInt(productDto.getProductId());

        DbObject dbObject = new DbObject(productDto.getProductId(),productDto.getUnitStock());

        boolean exists = cartRepository.existsById(productDto.getUserId());

        if(!exists)
        {
            ArrayList<DbObject> tempArray = new ArrayList<>();
            tempArray.add(dbObject);

            Product tempProduct = cartRepository.save(new Product(productDto.getUserId(), tempArray ));

            cartRepository.save(tempProduct);
        }
        else
        {
            Product tempProduct = cartRepository.findById(productDto.getUserId()).get();

            ArrayList<DbObject> tempArray = tempProduct.getProductList();
            tempArray.add(dbObject);
            tempProduct.setProductList(tempArray);
            cartRepository.save(tempProduct);
        }

        return true;

    }

    @Override
    public boolean removeFromCart(ProductDto productDto) {

        boolean exists = cartRepository.existsById(productDto.getUserId());

        if(!exists)
        {
            //Throw exception
        }

        Product tempProduct = cartRepository.findById(productDto.getUserId()).get();

        ArrayList<DbObject> tempArray = tempProduct.getProductList();
        DbObject mainObject=null;

        //#################Need boolean var to check if product id exists

        int objectIndex=0;
        int tempProductId=Integer.parseInt(productDto.getProductId());
        int tempDbProductId;


        for(DbObject dbObject : tempArray)
        {
            tempDbProductId = Integer.parseInt(dbObject.getProductId());

            if(tempDbProductId == tempProductId )
            {
                mainObject = dbObject;
                break;
            }
            objectIndex++;
        }

        if(mainObject.getUnitStock() > productDto.getUnitStock())
        {
            int unitStock = mainObject.getUnitStock();

            unitStock = unitStock - productDto.getUnitStock();

            mainObject.setUnitStock(unitStock);
            tempProduct.setProductList(tempArray);
            cartRepository.save(tempProduct);
        }
        else
        {
            tempArray.remove(objectIndex);
            tempProduct.setProductList(tempArray);
            cartRepository.save(tempProduct);
        }

        return true;
    }

    @Override
    public List<ProductDto> cartListItems(ProductDto productDto) {

        boolean exists = cartRepository.existsById(productDto.getUserId());

        if(!exists)
        {
            //Throw exception
        }

        Product tempProduct = cartRepository.findById(productDto.getUserId()).get();

        ArrayList<DbObject> tempArray = tempProduct.getProductList();
        ArrayList<ProductDto> dtoList = new ArrayList<ProductDto>();

        ProductDto tempProductDto = new ProductDto();

        for(DbObject dbObject : tempArray)
        {
            tempProductDto.setUserId(productDto.getUserId());
            tempProductDto.setProductId(dbObject.getProductId());
            tempProductDto.setUnitStock(dbObject.getUnitStock());

            dtoList.add(tempProductDto);
        }
        return dtoList;
    }
}


/*


        DbObject keyObject = new DbObject((long)key,productDto.getUserId());

        boolean exists = cartRepository.existsById(keyObject);

        if(!exists)
        {
            //Throw exception
        }

        Product tempProduct = cartRepository.findById(keyObject).get();

        if(tempProduct.getUnitStock() > productDto.getUnitStock() )//Handle for greater than quantity requests
        {
            int unitStock = tempProduct.getUnitStock();

            unitStock = unitStock - productDto.getUnitStock();

            tempProduct.setUnitStock(unitStock);

            cartRepository.save(tempProduct);
        }
        else
        {
            cartRepository.delete(tempProduct);
        }

 */