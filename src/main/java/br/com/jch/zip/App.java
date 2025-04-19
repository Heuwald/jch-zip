package br.com.jch.zip;

import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args )
	{
		//		JchZip jchZip = new JchZip(new File("D:/testes/zipTest").toPath(), new File("D:/testes/zipado").toPath());
		//		try {
		//			jchZip.zip();
		//		} catch (IOException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}

		JchUnzip jchZip = new JchUnzip();
		try {
			jchZip.unzip(new File("D:/testes/zipado.zip").toPath(), new File("D:/testes/zipado").toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
