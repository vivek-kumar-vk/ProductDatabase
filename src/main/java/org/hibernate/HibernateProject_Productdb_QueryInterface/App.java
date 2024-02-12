package org.hibernate.HibernateProject_Productdb_QueryInterface;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args )
	{
		Product product = new Product();
		product.setProductId(101);
		product.setProductName("Perfume");
		product.setProductBrand("Armani");
		product.setProductCategory("Personal");
		product.setProductPrice(25000);
		product.setProductStatus("In-Stock");

		ClassProductService classProductService = new ClassProductService();

		System.out.println("******************Insert********************");
		classProductService.addProduct(product);

		System.out.println("************findProductById****************");
		List<Product>list =  classProductService.findProductById(101);

		for(Product list1 : list) {
			System.out.println(list1);
		}
		System.out.println("************findProductByName****************");

		List<Product>list2 =  classProductService.findProductByName("Perfume");

		for(Product list3 : list2) 
		{
			System.out.println(list3);
		}

		System.out.println("************findProductByBrand****************");

		List<Product>list4 =  classProductService.findProductBetweenPrices(20000 , 30000);

		for(Product list5 : list4) 
		{
			System.out.println(list5);
		}
		System.out.println("************findProductByCategory****************");
		List<Product>list6 =  classProductService.findAllProductByCategory("Personal");

		for(Product list7 : list6) 
		{
			System.out.println(list7);
		}

		System.out.println("************updateProductStatus****************");
		classProductService.updateProductStatus(101);

		System.out.println("************updateProductPriceByName****************");
		classProductService.updateProductPriceByName("Perfume" ,25000 );

		System.out.println("************deleteProductByBrand****************");
		classProductService.deleteProductByBrand("Armani");

		System.out.println("************deleteProductByCategory****************");
		classProductService.deleteProductByCategory("Personal");
	}
}
