package toh;

/*
   By Roman Andronov
 */

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

/*
   This will execute Toh as a stand-alone
   Java program.

   To execute it as a Java applet consult the
   TohApplet class in this package
*/

public
class TohFrame
	extends JFrame
{
	public
	TohFrame()
	{
		super();
		setTitle( "Towers Of Hanoi" );
		setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
	}

	public static void
	main( String[] args )
	{
		SwingUtilities.invokeLater( new Runnable()
		{
			public void
			run()
			{
				TohFrame		tohfrm = new TohFrame();

				tohfrm.tohpnl = new TohPanel( tohfrm );
				tohfrm.pack();
				tohfrm.setLocationRelativeTo( null );
				tohfrm.setVisible( true );
			}
		});

	}

	private TohPanel		tohpnl = null;
}
