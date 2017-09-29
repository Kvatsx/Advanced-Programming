package TestCasesLab7;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import MusicApp.MusicApp;
import MusicApp.Playlist;
import MusicApp.Song;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	MyTest1.class,
	MyTest2.class,
	MyTest3.class
})

public class TestSuite { }
