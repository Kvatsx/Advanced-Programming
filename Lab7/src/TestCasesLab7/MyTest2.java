package TestCasesLab7;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import MusicApp.MusicApp;
import MusicApp.Playlist;
import MusicApp.Song;

/* Delete a song then checking the playlist using show songs.*/
public class MyTest2 {

	@Test
	public void testDelete() throws IOException, ClassNotFoundException {
		MusicApp myApp = new MusicApp();
		Playlist D = (Playlist)myApp.deserialize("TestFile");
		D.Delete("K1");
		String ans = "Name: K2"+'\n'+"Singer: bb"+'\n'+"Duration: 3"+'\n'+"Name: K3"+'\n'+"Singer: cc"+'\n'+"Duration: 4"+'\n';
		assertEquals(D.Show(),ans);
	}
}
