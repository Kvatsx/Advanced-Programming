package TestCasesLab7;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import MusicApp.MusicApp;
import MusicApp.Playlist;
import MusicApp.Song;

/* Adding a song then checking the playlist using show songs.*/
public class MyTest1 {

	@Test
	public void testAdd() throws IOException, ClassNotFoundException {
		MusicApp myApp = new MusicApp();
		Playlist D = (Playlist) myApp.deserialize("TestFile");
		Song mysong = new Song("Sot","Om",100);
		D.add(mysong);
		String ans = "Name: K1"+'\n'+"Singer: aa"+'\n'+"Duration: 2"+'\n'+"Name: K2"+'\n'+"Singer: bb"+'\n'+"Duration: 3"+'\n'+"Name: K3"+'\n'+"Singer: cc"+'\n'+"Duration: 4"+'\n'+"Name: Sot"+'\n'+"Singer: Om"+'\n'+"Duration: 100"+'\n';
		assertEquals(D.Show(),ans);
	}
}
