package toh;

/*
  By Roman Andronov
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;


class DiskView
	extends JLabel
{
	DiskView( TohPanel tohpnl, int dn )
	{
		super();

		myDiskNumber = dn;
		pnlToh = tohpnl;

		gbc.weighty = 0.1;
		gbc.fill = GridBagConstraints.VERTICAL;
		
		setOpaque( true );
		setBackground( DISK_CLR );
		setPreferredSize(
			new Dimension( ( myDiskNumber + 2 ) * TohPanel.MIN_HEIGHT,
				TohPanel.MIN_HEIGHT ) );
		setBorder( BorderFactory.createBevelBorder( BevelBorder.RAISED ) );
	}

	int
	getDiskNumber()
	{
		return myDiskNumber;
	}

	void
	add( int poleNumber )
	{
		pnlToh.pnlBoard.remove( pnlToh.poleViews[ poleNumber ] );
		gbc.gridx = poleNumber;
		gbc.gridy = TohPanel.MAX_DISKS - pnlToh.poles[ poleNumber ].diskCount() + 2;
		pnlToh.pnlBoard.add(this, gbc );
		pnlToh.poleViews[ poleNumber ].add();
	}

	void
	rm( int poleNumber )
	{
		pnlToh.pnlBoard.remove( this );
		pnlToh.pnlBoard.remove( pnlToh.poleViews[ poleNumber ] );
		pnlToh.poleViews[ poleNumber ].add();
	}

	static final Color			DISK_CLR = Color.LIGHT_GRAY;
	static final Color			CAND_DISK_CLR = new Color( 153, 204, 255 );

	private final GridBagConstraints	gbc = new GridBagConstraints();
	private final TohPanel			pnlToh;
	private final int			myDiskNumber;
}
