// Code By:- Kaustav Vats (2016048)
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

class Knight{
	private String Name;
	private int x,y;
	private int Size;
	private Stack MagicBox;
	
	public Knight(String name, int x, int y, int size)
	{
		MagicBox = new Stack();
		this.Name = name;
		this.x = x;
		this.y = y;
		this.Size = size;
	}
	public void Insert(Object z)
	{
		MagicBox.push(z);
	}
	public void Remove()
	{
		MagicBox.pop();
	}
	public Object getCoordinate() throws NonCoordinateException
	{
		if ( MagicBox.peek() instanceof Coordinates )
		{
			return MagicBox.pop();
		}
		else
		{
			throw new NonCoordinateException("Not a coordinate Exception "+ MagicBox.peek());
		}
	}
	public int BoxSize()
	{
		return this.Size;
	}
	public int getSize() throws StackEmptyException
	{
		if ( MagicBox.size() == 0 )
		{
			throw new StackEmptyException("Stack Empty exception");
		}
		return this.MagicBox.size();
	}
	public String getName()
	{
		return this.Name;
	}
	public void setx(int i)
	{
		this.x = i;
	}
	public int getx()
	{
		return this.x;
	}
	public void sety(int i)
	{
		this.y = i;
	}
	public int gety()
	{
		return this.y;
	}
}
class Coordinates {
	private int x,y;
	public Coordinates(int xc,int yc)
	{
		this.x = xc;
		this.y = yc;
	}
	public int getx()
	{
		return this.x;
	}
	public int gety()
	{
		return this.y;
	}
}
class NonCoordinateException extends Exception {
	public NonCoordinateException(String message)
	{
		super(message);
	}
}
class StackEmptyException extends Exception {
	public StackEmptyException(String message)
	{
		super(message);
	}
}
class OverlapException extends Exception {
	public OverlapException(String message)
	{
		super(message);
	}
}
class QueenFoundException extends Exception {
	public QueenFoundException(String message)
	{
		super(message);
	}
}

public class Game {
	private int cKnight;
	private int xIteration;
	private int xQueen,yQueen;
	private ArrayList<Knight> Arr;
	
	public Game(int ck, int xi, int xq, int yq)
	{
		this.Arr = new ArrayList<Knight>();
		this.cKnight = ck;
		this.xIteration = xi;
		this.xQueen = xq;
		this.yQueen = yq;
	}
	
	public void add(Knight z)
	{
		Arr.add(z);
	}
	public Knight get(int index)
	{
		return Arr.get(index);
	}
	public int getcKinght()
	{
		return cKnight;
	}
	public void checkKnight(int i, int cx, int cy) throws OverlapException
	{
		if ( i < Arr.size() )
		{
			for ( int j=i; j<Arr.size(); j++ )
			{
				if ( Arr.get(j).getx() ==  cx && Arr.get(j).gety() == cy )
				{
					String nam = Arr.get(j).getName();
					Arr.remove(j);
					throw new OverlapException("Knights Overlap Exception "+nam);
				}
			}
		}
	}
	public void checkQueen(int xi, int yi) throws QueenFoundException
	{
		if ( xi == xQueen && yi == yQueen )
		{
			throw new QueenFoundException("Queen has been Found. Abort!");
		}
	}
	public void StartGame() throws FileNotFoundException, UnsupportedEncodingException, NonCoordinateException, StackEmptyException, OverlapException, QueenFoundException 
	{
		Collections.sort(Arr, new Comparator<Knight>() {
			public int compare(Knight a, Knight b) 
			{
				return a.getName().compareTo(b.getName());
			}
				});
		PrintWriter w = new PrintWriter("./src/output.txt", "UTF-8");
		boolean GameStatus = true;
		int i=0;
		int Round = 1;
		int count = 0;
		while ( count != Arr.size() && GameStatus )
		{
			System.out.println("Infinite boop"+" Check");
			Knight currentKnight = Arr.get(i);
			int exc = 0;
			try 
			{
				w.println((Round)+" "+currentKnight.getName()+" "+currentKnight.getx()+" "+currentKnight.gety());
				if ( currentKnight.getSize() > 0 )
				{
					Coordinates myLocation = (Coordinates) currentKnight.getCoordinate();
					currentKnight.setx(myLocation.getx());
					currentKnight.sety(myLocation.gety());
					checkKnight(i+1, myLocation.getx(), myLocation.gety());
					checkQueen(myLocation.getx(), myLocation.gety());
				}
			}
			catch (NonCoordinateException e)
			{
				exc = 1;
				currentKnight.Remove();
				w.println("NonCoordinateException: "+e.getMessage());
			}
			catch (StackEmptyException e)
			{
				exc = 2;
				count++;
				w.println("StackEmptyException: "+e.getMessage());
			}
			catch (OverlapException e)
			{
				exc = 3;
				w.println("OverlapException: "+e.getMessage());
			}
			catch (QueenFoundException e)
			{
				exc = 4;
				GameStatus = false;
				w.println("QueenFoundException: "+e.getMessage());
			}
			if ( exc == 0 )
			{
				w.println("No exception "+ currentKnight.getx()+" "+currentKnight.gety());
			}
			i++;
			if ( i == Arr.size() )
			{
				Round++;
				i=0;
			}
		}
		w.close();
	}
	
	public static void main(String[] args) throws IOException, NonCoordinateException, StackEmptyException, OverlapException, QueenFoundException {
		Reader.init(System.in);
		Game game = new Game(Reader.nextInt(), Reader.nextInt(), Reader.nextInt(), Reader.nextInt());
		for ( int i=1; i<=game.getcKinght(); i++ )
		{
			InputStream in = new FileInputStream("./src/" + i + ".txt");
			Reader.init(in);
			Knight myKnight = new Knight(Reader.next(), Reader.nextInt(), Reader.nextInt(), Reader.nextInt());
			for ( int j=0; j<myKnight.BoxSize(); j++ )
			{
				String type = Reader.next();
				if ( type.equals("String") )
				{
					myKnight.Insert(Reader.next());
				}
				else if ( type.equals("Integer") )
				{
					myKnight.Insert(Reader.nextInt());
				}
				else if ( type.equals("Float") )
				{
					myKnight.Insert(Reader.nextFloat());
				}
				else
				{
					Coordinates value = new Coordinates(Reader.nextInt(), Reader.nextInt());
					myKnight.Insert(value);
				}
			}
			game.add(myKnight);
		}
		game.StartGame();
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