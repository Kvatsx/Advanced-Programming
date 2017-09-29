package MusicApp;

import java.io.Serializable;

public class Song implements Serializable {
	private final String Name;
	private final String Singer;
	private final int Duration;
	public Song(String name, String singer, int duration)
	{
		this.Name = name;
		this.Singer = singer;
		this.Duration = duration;
	}
	public String getName()
	{
		return this.Name;
	}
	@Override
	public String toString()
	{
		return "Name: "+ this.Name + '\n' + "Singer: "+this.Singer+'\n'+"Duration: "+String.valueOf(this.Duration)+" sec";
	}
	public String getDetails()
	{
		return "Name: "+ this.Name + '\n' + "Singer: "+this.Singer+'\n'+"Duration: "+String.valueOf(this.Duration)+" sec"+'\n';
	}
}