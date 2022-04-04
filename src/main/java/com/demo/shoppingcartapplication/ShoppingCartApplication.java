package com.demo.shoppingcartapplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.shoppingcartapplication.entity.Apparal;
import com.demo.shoppingcartapplication.entity.Book;
import com.demo.shoppingcartapplication.entity.Cart;
import com.demo.shoppingcartapplication.entity.User;
import com.demo.shoppingcartapplication.repository.ApparalRepository;
import com.demo.shoppingcartapplication.repository.BookRepository;
import com.demo.shoppingcartapplication.repository.CartRepository;
import com.demo.shoppingcartapplication.repository.UserRepository;

@SpringBootApplication
public class ShoppingCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
		System.out.println("#### Server is up and application can be accessed at http://localhost:8080 ####");

	}

	@Bean
	CommandLineRunner runner(BookRepository bookRepository, ApparalRepository apparalRepository,
			UserRepository userRepository, CartRepository cartRepository) {
		return args -> {
			bookRepository.save(new Book(1, "The Alchemist", 208, 100, "Quest, adventure, fantasy", "Paulo Coelho",
					"HarperTorch", "https://whitcoulls-co-nz.imgix.net/products/79/36/5923679_MAIN~1551959965.jpg?w=416&h=620&fit=fill&auto=format&fill=solid&fill-color=ffffff&s=8cf25124bef4ae41b11bbb38e9f68336"));
			bookRepository.save(new Book(2, "Anne Frank", 500, 20, "Autobiography", "Anne Frank", "Contact Publishing", "https://rukminim1.flixcart.com/image/832/832/jrwdyfk0/book/4/3/6/the-diary-of-a-young-girl-original-imaeycnzccfkkgg4.jpeg"));
			bookRepository.save(new Book(3, "Windup", 1449, 16, "Fiction ", "Shaffer Brooke M", "Black Bear Publishing, LLC", "https://rukminim1.flixcart.com/image/832/832/jtuej680/book/2/6/4/windup-original-imaffffnhqe8zany.jpeg"));
			bookRepository.save(new Book(4, "Relativity - The Special and The General Theory by Einstein Albert", 147, 10, "Science", "Einstein Albert", "Navyug Publishers", "https://rukminim1.flixcart.com/image/832/832/j4rc8sw0/book/6/5/2/relativity-original-imaevhcjpyegp3q5.jpeg"));
			bookRepository.save(new Book(5, "Ikigai - The Japanese Secret to a Long and Happy Life", 784, 20,
					"Self-Help", "Garcia Hector", "Cornerstone",
					"https://rukminim1.flixcart.com/image/832/832/jeiukcw0/book/8/9/5/ikigai-original-imaf36tfg6h9hx2x.jpeg"));
			bookRepository.save(new Book(6, "Hazaron Khawaishen Aisi: The Wonderful World of Urdu Ghazals", 1449, 16,
					"Poetry", "Anisur Rahman", "HarperCollins Publishers, LLC",
					"https://rukminim1.flixcart.com/image/832/832/jp02t8w0/book/3/9/3/hazaron-khawaishen-aisi-the-wonderful-world-of-urdu-ghazals-original-imafb43948kz9sdf.jpeg"));

			apparalRepository.save(new Apparal(7, "T-Shirt", 499, 20, "V-neck Maroon T-Shirt", "Flying Machine",
					"V-neck",
					"https://rukminim1.flixcart.com/image/800/960/k4bms280/t-shirt/5/b/t/m-11-v-neck-try-this-original-imaf969m4due9wbm.jpeg"));
			apparalRepository.save(new Apparal(8, "Jeans", 2341, 17, "Slim Men Blue Jeans", "Lee", "Slim Jeans",
					"https://rukminim1.flixcart.com/image/800/960/k0zlsi80/jean/d/t/h/32-maw19jn139-metronaut-original-imafkmgrmarsbc9f.jpeg"));
			apparalRepository.save(new Apparal(9, "Blazer", 3945, 17, "Full Sleeve", "Peter England",
					" Full Sleeve Blazer",
					"https://rukminim1.flixcart.com/image/800/960/k1pbpu80/blazer/7/z/y/42-pibzetlpp32866-peter-england-original-imafh7z6jvhzhu5m.jpeg"));
			apparalRepository.save(new Apparal(10, "Kurti", 1546, 13, "Straight Kurta", "Libas",
					"Solid Pure Cotton Straight Kurta",
					"https://rukminim1.flixcart.com/image/800/960/jzrb53k0/kurta/m/2/z/xl-8256mj-blue-libas-original-imafjpczz3cjf2gd.jpeg"));
			apparalRepository.save(new Apparal(11, "Flared Skirt", 1299, 18, "Flared Maroon Skirt", "Varanga",
					"Floral Print Flared Maroon Skirt",
					"https://rukminim1.flixcart.com/image/800/960/k15bjww0/skirt/q/p/j/xxl-vars219547-varanga-original-imafks5gt5scjmhg.jpeg?q=50"));

			apparalRepository.save(new Apparal(12, "Casual Top", 1899, 29, "3/4 Sleeve Floral Print Maroon Top",
					"Selvia ", "Floral Print Top",
					"https://rukminim1.flixcart.com/image/800/960/k55n0y80/top/t/3/h/xl-68tk2163-74tk2235-selvia-original-imafh36aebe45ntt.jpeg?q=50"));

			Cart cart1 = new Cart();
			cartRepository.save(cart1);
			userRepository.save(new User(101, "Suraiya", cart1));

		};
	}

	private static final String PATH = "/mycart";

	@RequestMapping(value = PATH)
	public String error() {
		return "forward:/index.html";
	}

	public static String getPath() {
		return PATH;
	}

}
