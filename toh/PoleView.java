package toh;

/*
  By Roman Andronov
 */

import java.awt.Insets;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

class PoleView
	extends JButton
	implements ActionListener
{
	PoleView( TohPanel tohpnl, int pn )
	{
		super();

		myPoleNumber = pn;
		pnlToh = tohpnl;

		setOpaque( true );
		setBackground( POLE_CLR );
		setMinimumSize( MIN_POLE_SIZE );
		addActionListener( this );
		
		gbc.weightx = gbc.weighty = 1.0;
		gbc.gridy = 0;
		gbc.insets = POLE_INSETS;
		gbc.gridx = myPoleNumber;
		gbc.fill = GridBagConstraints.VERTICAL;
	}

	public void 
	actionPerformed( ActionEvent ae )
	{
		int		srctdn = -1;
		int		mytdn = -1;
		int		mydc = -1;
		Pole		srcpole = null;
		Disk		disk = null;
		DiskView	dv = null;

		if ( pnlToh.gameOver )
		{
			return;
		}

		if ( pnlToh.srcPole < 0 )
		{
			if ( pnlToh.poles[ myPoleNumber ].diskCount() <= 0 )
			{
				return;
			}

			pnlToh.srcPole = myPoleNumber;
			srcpole = pnlToh.poles[ pnlToh.srcPole ];
			srctdn = srcpole.topDiskNumber();
			dv = pnlToh.diskViews[ srctdn ];
			dv.setBackground( DiskView.CAND_DISK_CLR );

			return;
		}

		if ( myPoleNumber == pnlToh.srcPole )
		{
			/*
			  Cancel this move
			 */
			srcpole = pnlToh.poles[ pnlToh.srcPole ];
			srctdn = srcpole.topDiskNumber();
			dv = pnlToh.diskViews[ srctdn ];
			dv.setBackground( DiskView.DISK_CLR );
			pnlToh.srcPole = -1;

			return;
		}

		srcpole = pnlToh.poles[ pnlToh.srcPole ];
		srctdn = srcpole.topDiskNumber();
		mytdn = pnlToh.poles[ myPoleNumber ].topDiskNumber();
		mydc = pnlToh.poles[ myPoleNumber ].diskCount();

		dv = pnlToh.diskViews[ srctdn ];
		dv.setBackground( DiskView.DISK_CLR );

		if ( mydc > 0 && srctdn > mytdn )
		{
			pnlToh.srcPole = -1;

			return;
		}

		disk = srcpole.rmDisk();
		pnlToh.poles[ myPoleNumber ].addDisk( disk );
		pnlToh.srcPole = -1;
		pnlToh.movesCount++;
		pnlToh.lblMovesCount.setText( "" + pnlToh.movesCount );
		pnlToh.checkEndOfGame();
	}

	int
	getPoleNumber()
	{
		return myPoleNumber;
	}

	void
	add()
	{
		int		dc = pnlToh.poles[ myPoleNumber ].diskCount();

		pnlToh.pnlBoard.remove( this );
		gbc.gridheight = TohPanel.MAX_DISKS - dc + 2;
		setPreferredSize( new Dimension( TohPanel.MIN_HEIGHT,
			TohPanel.MIN_HEIGHT * gbc.gridheight ) );
		pnlToh.pnlBoard.add( this, gbc );
	}

	static final Insets		POLE_INSETS = new Insets( 5, 0, 0, 0 );
	static final Color		POLE_CLR = new Color( 227, 224, 207 );
	static final Dimension		MIN_POLE_SIZE =
		new Dimension( TohPanel.MIN_HEIGHT, 2 * TohPanel.MIN_HEIGHT );

	private final int			myPoleNumber;
	private final TohPanel			pnlToh;
	private final GridBagConstraints	gbc = new GridBagConstraints();
}
