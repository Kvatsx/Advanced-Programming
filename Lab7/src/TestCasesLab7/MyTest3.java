package TestCasesLab7;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import MusicApp.MusicApp;
import MusicApp.Playlist;
import MusicApp.Song;

/* Search a song and validates it's Details.*/
public class MyTest3 {

	@Test
	public void testSearch() throws IOException, ClassNotFoundException {
		MusicApp myApp = new MusicApp();
		Playlist D = (Playlist) myApp.deserialize("TestFile");
		String ans = "Name: K1"+'\n'+"Singer: aa"+'\n'+"Duration: 2"+'\n';
		assertEquals(D.Search("K1"),ans);
	}
}
