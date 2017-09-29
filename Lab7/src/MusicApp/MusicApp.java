// Kaustav Vats(2016048)
package MusicApp;

import java.io.*;
import java.util.*;
import MusicApp.Song;
import MusicApp.Playlist;

public class MusicApp {
	
	public static void serialize(Playlist myPlaylist)throws IOException {
		ObjectOutputStream out = null;
		try {
			out  = new ObjectOutputStream(new FileOutputStream("./src/"+myPlaylist.getName()+".txt"));
			out.writeObject(myPlaylist);
		}
		finally {
			out.close();
		}
	}
	
	public static Playlist deserialize(String name)throws IOException, ClassNotFoundException {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream("./src/"+name+".txt"));
			return (Playlist) in.readObject();
		}
		finally {
			in.close();
		}
	}
	
//	public void add(Playlist p) throws IOException
//	{
//		System.out.print("Enter Song Name: ");
//		String sname = Reader.next();
//		System.out.print("Enter Singer Name: ");
//		String si = Reader.next();
//		System.out.print("Enter Duration of Song: ");
//		int d = Reader.nextInt();
//		p.add(new Song(sname,si,d));
//	}
//	public void Delete(Playlist p) throws IOException
//	{
//		System.out.print("Enter Song name to Delete: ");
//		String nam = Reader.next();
//		p.Delete(nam);
//	}
//	public void Search(Playlist p) throws IOException
//	{
//		System.out.print("Enter Song name to Search: ");
//		String nam = Reader.next();
//		p.Search(nam);
//	}
	public static void main(String[] args)throws IOException, ClassNotFoundException {
		Reader.init(System.in);
		MusicApp MyApp = new MusicApp();
		Song A = new Song("K1","aa",2);
		Song B = new Song("K2","bb",3);
		Song C = new Song("K3","cc",4);
		Playlist D = new Playlist("Kaustav");
		D.add(A);
		D.add(B);
		D.add(C);
		serialize(D);
		while(true)
		{
			System.out.print("Enter Valid Name of Playlist: ");
			String name = Reader.next();
			Playlist p = deserialize(name);
			System.out.println("Total Songs:  "+p.getSize());
			boolean Flag = false;
			boolean Flag2 = false;
			while(true)
			{
				System.out.println("------------ Menu -----------");
				System.out.println("1: Add");
				System.out.println("2: Delete");
				System.out.println("3: Search");
				System.out.println("4: Show");
				System.out.println("5: Back To Menu");
				System.out.println("6: Exit");
				System.out.println("-----------------------------");
				System.out.print("Enter a valid Integer: ");
				int i = Reader.nextInt();
				if ( i == 1 )
				{
					System.out.print("Enter Song Name: ");
					String sname = Reader.next();
					System.out.print("Enter Singer Name: ");
					String si = Reader.next();
					System.out.print("Enter Duration of Song: ");
					int d = Reader.nextInt();
					p.add(new Song(sname,si,d));
//					serialize(p);
//					MyApp.add(p);
				}
				else if ( i == 2 )
				{
					System.out.print("Enter Song name to Delete: ");
					String nam = Reader.next();
					p.Delete(nam);
//					serialize(p);
//					MyApp.Delete(p);
				}
				else if ( i == 3 )
				{
					System.out.print("Enter Song name to Search: ");
					String nam = Reader.next();
					System.out.println(p.Search(nam));
//					MyApp.Search(p);
				}
				else if ( i == 4 )
				{
					System.out.println(p.Show());
				}
				else if ( i == 5 )
				{
					serialize(p);
					Flag = true;
					break;
				}
				else
				{
					serialize(p);
					Flag2 = true;
					break;
				}
			}
			if ( Flag )
			{
				continue;
			}
			if ( Flag2 )
			{
				break;
			}
		}
	}
}

/** Class for buffered reading int and double values */
class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                     new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }
	
    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
    
    static float nextFloat() throws IOException {
    	return Float.parseFloat( next() );
    }
}