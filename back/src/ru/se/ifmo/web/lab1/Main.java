import com.fastcgi.*;
import java.nio.charset.StandardCharsets;
import java.io.*;
import java.nio.*;

public class Main {
	public static void main(String[] args) {
		FCGIInterface fcgiInterface = new FCGIInterface();
		while (fcgiInterface.FCGIaccept() >= 0) {
			System.out.println(FCGIInterface.request.params.getProperty("QUERY_STRING"));
		}
	}
}
