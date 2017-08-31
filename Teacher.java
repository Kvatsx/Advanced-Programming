// Code By:- Kaustav Vats
import java.io.*;
import java.util.*;
import java.math.*;
import java.lang.*;

class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    private T Data;
    private int Position;
    private Node<T> Left,Right;
    private T Sum;

    public Node()
    {
    	//
    }
    public Node(T d)
    {
    	this.Data = d;
    	this.Left = null;
    	this.Right = null;
    }
    public T getSum()
    {
    	return this.Sum;
    }
    public void setSum(T g)
    {
    	this.Sum = g;
    }
    public T getData()
    {
    	return this.Data;
    }
    public Node<T> getLeft()
    {
    	return this.Left;
    }
    public Node<T> getRight()
    {
    	return this.Right;
    }
    public void setLeft(Node<T> l)
    {
    	this.Left = l;
    }
    public void setRight(Node<T> r)
    {
    	this.Right = r;
    }
    public void setPosition(int x)
    {
    	this.Position = x;
    }
    public int getPosition()
    {
    	return Position;
    }
//	public void BST(String[] z,int count,String type)
//	{
//		Node<T> Root = null;
//		if ( type.equals("Integer") )
//		{
//			for (int i=0; i<count; i++)
//			{
//				T value = (T) z[i];
//				Root = Insert(Root,value);
//			}
//		}
//	}
	public Node<T> Insert(Node<T> myR,T Value)
    {
		Node<T> Val = new Node<T>(Value);
    	if ( myR == null )
    	{
    		myR = Val;
    	}
    	else
    	{
    		if ( myR.compareTo(Val) <= 0 )
    		{
//    			pos = 2*pos+1;
    			myR.setLeft(Insert(myR.getLeft(), Value));
    		}
    		else
    		{
//    			pos = 2*pos+1;
    			myR.setRight(Insert(myR.getRight(), Value));
    		}
    	}
    	return myR;
    }
	@Override
	public int compareTo(Node<T> second) {
		System.out.println(this.getData()+" "+second.getData());
		return this.getData().compareTo(second.getData());
	}
}

//class BST<T extends Comparable<T>> 
//{
//	private Node<T> Root;
//	
//	public void add(T Value)
//	{
//		Root = Insert(Root,Value,0);
//	}
//}
class Teacher<T extends Comparable<T>> extends Node<T>{
	private ArrayList<Node> Tree_List;
	private static int numTrees;
	private static int numStudents;
	private static int Chocolates = 0;
	private HashMap<Integer,ArrayList<Object>> Gifts;
	public Teacher()
	{
		Tree_List = new ArrayList<Node>();
		Gifts = new HashMap<Integer,ArrayList<Object>>();
		for ( int i=1; i<=numStudents; i++ )
		{
			ArrayList<Object> a = new ArrayList<Object>();
			Gifts.put(i, a);
		}
	}
	public void add(Node<T> data)
	{
		this.Tree_List.add(data);
	}
	public void add_map(int i,T sum)
	{
		ArrayList<Object> asd = Gifts.get(i);
		System.out.println(1+" "+"Sum "+sum);
		asd.add(sum);
		Gifts.put(i, asd);
	}
	public ArrayList<Object> get_map(int i)
	{
		return Gifts.get(i);
	}
	public Node<T> get(int index)
	{
		return Tree_List.get(index);
	}
	@SuppressWarnings("unchecked")
//	public T Addition (T x,T y)
//	{
//		if(y instanceof Integer)
//		{
//			return (T)((Integer)((Integer)(x)+(Integer)(y)));
//		}
//		if(y instanceof String)
//		{
//			return (T)(String)((String)(x)+(String)(y));
//		}
//		if ( y instanceof Float )
//		{
//			return (T)(Float)((Float)(x)+(Float)(y));
//		}
//		else
//		{
//			System.out.println("-1-1-1-1-1");
//			return null;
//		}
//	}

	public ArrayList<T> Inorder(Node<T> root,ArrayList<T> fgh)
	{
		if ( root != null )
		{
			fgh = Inorder(root.getLeft(),fgh);
//			d = Addition(d,root.getData());
			fgh.add(root.getData());
			fgh = Inorder(root.getRight(),fgh);
		}
		return fgh;
	}
	
	
    public static void main(String[] args)throws IOException {
    	Reader.init(System.in);
    	BSTFilesBuilder Input_gen = new BSTFilesBuilder();
    	numStudents = Reader.nextInt();
    	numTrees = Reader.nextInt();
    	Input_gen.createBSTFiles(numStudents, numTrees);
    	Teacher abcd = new Teacher();
        for ( int i=1; i<=numTrees; i++ )
        {
//        	Scanner inFile1 = new Scanner(new File("./src/" + i + ".txt")).useDelimiter(" ");
//          BufferedReader br = new BufferedReader(new FileReader("./src/" + i + ".txt"));
        	InputStream in = new FileInputStream("./src/" + i + ".txt");
        	Reader.init(in);
        	String z = Reader.next();
        	System.out.println(z);
            int count = Reader.nextInt();
            if ( z.equals("Integer") )
            {
            	int kaustav = 0;
            	Node<Integer> q = null;
            	for ( int j=0; j<count; j++ )
            	{
//            		System.out.println("In");
            		int ghj = Reader.nextInt();
            		kaustav+=ghj;
            		q = abcd.Insert(q,ghj);
            	}
            	q.setSum(kaustav);
            	abcd.add(q);
            	int roll=0;
            	ArrayList<Integer> fgh = new ArrayList<Integer>();
        		fgh = abcd.Inorder(q,fgh);
        		for ( int j=fgh.size()-1; j>= 0; j-- )
        		{
        			if ( fgh.get(j) == q.getData() )
        			{
        				roll = j;
        				break;
        			}
        		}
//        		ArrayList<Object> jk = abcd.get_map(i);
//        		jk.add(a);
        		roll++;
        		abcd.add_map(roll, kaustav);
            }
            else if ( z.equals("Float") )
            {
            	float kaustav = 0;
            	Node<Float> q = null;
            	for ( int j=0; j<count; j++ )
            	{
//            		System.out.println("In");
            		float ghj = Reader.nextFloat();
            		q = abcd.Insert(q,ghj);
            		kaustav += ghj;
            	}
            	q.setSum(kaustav);
            	abcd.add(q);
            	int roll = 0;
            	ArrayList<Float> fgh = new ArrayList<Float>();
        		fgh = abcd.Inorder(q,fgh);
        		for ( int j=fgh.size()-1; j>= 0; j-- )
        		{
        			if ( fgh.get(j) == q.getData() )
        			{
        				roll = j;
        				break;
        			}
        		}
        		roll++;
        		abcd.add_map(roll, kaustav);
            }
            else
            {
            	String kaustav="";
            	Node<String> q = null;
            	for ( int j=0; j<count; j++ )
            	{
//            		System.out.println("In");
            		String ghj = Reader.next();
            		q = abcd.Insert(q,ghj);
            		kaustav += ghj;
            	}
            	q.setSum(kaustav);
            	abcd.add(q);
            	int roll = 0;
            	String g = "";
        		ArrayList<String> fgh = new ArrayList<String>();
        		fgh = abcd.Inorder(q,fgh);
        		for ( int j=fgh.size()-1; j>= 0; j-- )
        		{
        			if ( fgh.get(j).equals(q.getData()) )
        			{
        				roll = j;
        				break;
        			}
        		}
        		roll++;
        		abcd.add_map(roll, kaustav);
            }
        }
        int count =0;
        for ( int i=1; i<=numStudents; i++ )
        {
        	if ( abcd.get_map(i).size() == 0)
        	{
        		count++;
        	}
//        	System.out.println(i+" "+abcd.get_map(i));
        }
        PrintWriter w = new PrintWriter("./src/output.txt", "UTF-8");
        for ( int i=1; i<=numStudents; i++ )
        {
        	if ( abcd.get_map(i).size()>0 )
        	{
        		w.print(i+" ");
        		ArrayList<Object> a = abcd.get_map(i);
        		for ( int j=0; j<a.size(); j++ )
        		{
        			w.print(a.get(j)+" ");
        		}
        		w.println();
        	}
        }
        w.println(count);
        w.close();
//        for ( int i=0; i<numTrees; i++ )
//        {
//        	int roll = 0;
//        	if ( abcd.Tree_List.get(i) instanceof Integer )
//        	{
//        		ArrayList<Integer> fgh = new ArrayList<Integer>();
//        		int a = (Integer)abcd.Inorder((Node<Integer>) abcd.Tree_List.get(i),fgh);
//        		for ( int j=fgh.size()-1; j>= 0; j-- )
//        		{
//        			if ( fgh.get(j) == abcd.Tree_List.get(0) )
//        			{
//        				roll = j;
//        				break;
//        			}
//        		}
////        		ArrayList<Object> jk = abcd.get_map(i);
////        		jk.add(a);
//        		abcd.add_map(roll, abcd.Tree_List.get(i).getSum());
//        	}
//        	else if ( abcd.Tree_List.get(i) instanceof String )
//        	{
//        		String g = "";
//        		ArrayList<String> fgh = new ArrayList<String>();
//        		String a = (String)abcd.Inorder((Node<String>) abcd.Tree_List.get(i),fgh);
//        		for ( int j=fgh.size()-1; j>= 0; j-- )
//        		{
//        			if ( fgh.get(j).equals(abcd.Tree_List.get(0)) )
//        			{
//        				roll = j;
//        				break;
//        			}
//        		}
//        		abcd.add_map(roll, a);
//        	}
//        	else
//        	{
//        		ArrayList<Float> fgh = new ArrayList<Float>();
//        		float a = (Float)abcd.Inorder((Node<Float>) abcd.Tree_List.get(i),fgh);
//        		for ( int j=fgh.size()-1; j>= 0; j-- )
//        		{
//        			if ( fgh.get(j) == abcd.Tree_List.get(0) )
//        			{
//        				roll = j;
//        				break;
//        			}
//        		}
//        		abcd.add_map(roll, a);
//        	}
//        	
//        }
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