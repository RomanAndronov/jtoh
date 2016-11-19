package toh;

/*
  By Roman Andronov
 */

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.RootPaneContainer;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

class TohPanel
	extends JPanel
	implements ActionListener
{
	TohPanel( RootPaneContainer rpc )
	{
		super();
		init( rpc );
	}

	public void
	actionPerformed( ActionEvent ae )
	{
		Object		o = ae.getSource();

		if ( o instanceof JButton )
		{
			JButton		jb = ( JButton )o;

			if ( jb == jbNewGame )
			{
				newGame();
			}
		}
	}

	private void
	init( RootPaneContainer rpc )
	{
		gui = new TohGui( this );

		gui.init( rpc );

		newGame();
	}

	private void
	newGame()
	{
		int		nm = 0;
		String		txt = null;
		String		nod = ( String )cmbNumOfDisks.getSelectedItem();

		srcPole = -1;
		movesCount = 0;
		gameOver = false;
		numberOfDisks = Integer.parseInt( nod );

		/*
		  Remove all disks and their views
		 */
		for ( int i = 0; i < poles.length; i++ )
		{
			while ( poles[ i ].diskCount() > 0 )
			{
				poles[ i ].rmDisk();
			}
		}

		nm = ( int )Math.pow( 2, numberOfDisks ) - 1;
		nm = nm > 0 ? nm : 1;
		txt = "Can be done in " + nm + " moves";
		lblBase.setText( txt );

		/*
		  Always begin a new game with a left pole. This is important at
		  the end of the game - the check should exclude this pole, since
		  moving one disk from this pole and putting it back on should not
		  be a two-move solution.
		  Disks go on the pole in revese order:
		   larger disks first...
		 */
		for ( int i = numberOfDisks - 1; i >= 0; i-- )
		{
			poles[ 0 ].addDisk( disks[ i ] );
		}
		
		lblMovesCount.setText( "0" );
	}

	void
	checkEndOfGame()
	{
		boolean		    eog = false;

		/*
		  Check if the middle or the right pole has all the disks
		 */
		for ( int i = 1; i < poles.length; i++ )
		{
			if ( poles[ i ].diskCount() == numberOfDisks )
			{
				eog = true;
				break;
			}
		}

		if ( !eog )
		{
			return;
		}

		gameOver = true;

		lblBase.setText( lblBase.getText() + ", game over" );
	}

	static final int		MAX_DISKS = 7;
	static final int		MAX_POLES = 3;
	static final int		MIN_HEIGHT = 20;
	static final String[]		ALLOWED_DISK_NUMBERS = { "3", "4", "5", "6", "7" };
    
	TohGui				gui = null;

	JPanel				pnlBoard = null;
	JLabel				lblBase = null;

	JPanel				pnlCtrls = null;
	JButton				jbNewGame = null;
	JLabel				lblNumOfDisks = null;
	JComboBox			cmbNumOfDisks = null;
	JLabel				lblNumOfMoves = null;
	JLabel				lblMovesCount = null;
    
	Disk[]				disks = null;
	Pole[]				poles = null;

	DiskView[]			diskViews = null;
	PoleView[]			poleViews = null;

	int				movesCount = 0;
	int				numberOfDisks = 3;

	int				srcPole = -1;

	boolean				gameOver = true;
}
