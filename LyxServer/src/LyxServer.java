import javax.swing.JFrame;

import com.rta.gui.ServerBoard;

public class LyxServer
{

	public static void main(String[] args)
	{
		ServerBoard frame = new ServerBoard();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
