package MusicApp;

import java.io.Serializable;
import java.util.ArrayList;
import MusicApp.Song;

public class Playlist implements Serializable {
	private final String Name;
	private ArrayList<Song> Arr;
	
	public Playlist(String name)
	{
		this.Name = name;
		this.Arr = new ArrayList<Song>();
	}
	public String getName()
	{
		return this.Name;
	}
	public int getSize()
	{
		return this.Arr.size();
	}
	public void add(Song x)
	{
		Arr.add(x);
	}
	public void Delete(String name)
	{
		boolean Flag = true;
		for ( int i=0; i<Arr.size(); i++ )
		{
			if ( Arr.get(i).getName().equals(name) )
			{
				Flag = false;
				Arr.remove(i);
				System.out.println("Number of Songs in Playlist: "+Arr.size());
				break;
			}
		}
		if ( Flag )
		{
			System.out.println("Error: Name of Song does not exist");
		}
	}
	public String Search(String name)
	{
		boolean Flag = true;
		String a = "";
		for ( int i=0; i<Arr.size(); i++ )
		{
			if ( Arr.get(i).getName().equals(name) )
			{
				Flag = false;
				a = Arr.get(i).getDetails();
				break;
			}
		}
		if ( Flag )
		{
			a = "Error: Name of Song does not exist";
		}
		return a;
	}
	public String Show()
	{
		if ( Arr.size() == 0 )
		{
			return "No Song Exist.";
		}
		else
		{
			String a = "";
			for ( int i=0; i<Arr.size(); i++ )
			{
				a += Arr.get(i).getDetails();
			}
			return a;
		}
	}
}
