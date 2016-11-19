package toh;

/*
   By Roman Andronov
 */

import java.awt.Insets;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.RootPaneContainer;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

class TohGui
{
	TohGui( TohPanel pnltoh )
	{
		pnlToh = pnltoh;
	}

	void
	init( RootPaneContainer rpc )
	{
		GridBagLayout		gblo = new GridBagLayout();
		GridBagConstraints	gbc = new GridBagConstraints();
		int			cw = ( TohPanel.MAX_DISKS + 3 ) * TohPanel.MIN_HEIGHT;

		gbc.gridx = gbc.gridy = 0;
		gbc.weightx = gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = INSETS;

		rpc.getContentPane().setLayout( new GridBagLayout() );

		pnlToh.setLayout( new GridBagLayout() );
		pnlToh.setBorder( BorderFactory.createLineBorder( CLRGRAY ) );
		rpc.getContentPane().add( pnlToh, gbc );

		pnlToh.pnlBoard = new JPanel();
		pnlToh.pnlBoard.setBorder( BorderFactory.createLineBorder( CLRGRAY ) );
		gblo.columnWidths = new int[] { cw, cw, cw };
		pnlToh.pnlBoard.setLayout( gblo );
		pnlToh.add( pnlToh.pnlBoard, gbc );
		mkBoardPnl();

		gbc.gridy = 1;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		pnlToh.pnlCtrls = new JPanel();
		pnlToh.pnlCtrls.setBorder( BorderFactory.createLineBorder( CLRGRAY ) );
		pnlToh.pnlCtrls.setLayout( new GridBagLayout() );
		pnlToh.add( pnlToh.pnlCtrls, gbc );
		mkCtrlsPnl();
	}

	private void
	mkBoardPnl()
	{
		GridBagConstraints	gbc = new GridBagConstraints();

		pnlToh.pnlBoard.setBackground( BOARD_CLR );

		pnlToh.disks = new Disk[ TohPanel.MAX_DISKS ];
		pnlToh.diskViews = new DiskView[ TohPanel.MAX_DISKS ];
		for ( int i = 0; i < TohPanel.MAX_DISKS; i++ )
		{
			pnlToh.diskViews[ i ] = new DiskView( pnlToh, i );
			pnlToh.disks[ i ] = new Disk( i, pnlToh.diskViews[ i ] );
		}

		pnlToh.poles = new Pole[ TohPanel.MAX_POLES ];
		pnlToh.poleViews = new PoleView[ TohPanel.MAX_POLES ];
		for ( int i = 0; i < TohPanel.MAX_POLES; i++ )
		{
			pnlToh.poleViews[ i ] = new PoleView( pnlToh, i );
			pnlToh.poles[ i ] = new Pole( i, pnlToh.poleViews[ i ] );
		}

		pnlToh.numberOfDisks = 3;

		for ( int i = 0; i < pnlToh.poleViews.length; i++ )
		{
			pnlToh.poleViews[ i ].add();
		}

		pnlToh.lblBase = new JLabel();
		pnlToh.lblBase.setOpaque( true );
		pnlToh.lblBase.setBackground( BASE_CLR );
		pnlToh.lblBase.setForeground( TEXT_CLR );

		Border	bb = BorderFactory.createBevelBorder( BevelBorder.RAISED );
		Border	eb = BorderFactory.createEmptyBorder( 5, 5, 5, 5);
		pnlToh.lblBase.setBorder( BorderFactory.createCompoundBorder( bb, eb ) );

		pnlToh.lblBase.setHorizontalAlignment( SwingConstants.CENTER );
		pnlToh.lblBase.setVerticalAlignment( SwingConstants.CENTER );


		gbc.gridx = gbc.gridy = 0;
		gbc.gridy = TohPanel.MAX_DISKS + 2 /* For pole. */;
		gbc.insets = INSETS;
		gbc.gridwidth = TohPanel.MAX_POLES;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		pnlToh.pnlBoard.add( pnlToh.lblBase, gbc );
	}

	private void
	mkCtrlsPnl()
	{
		GridBagConstraints	gbc = new GridBagConstraints();

		gbc.gridx = gbc.gridy = 0;
		gbc.insets = INSETS;

		pnlToh.pnlCtrls.setBackground( BOARD_CLR );
		
		pnlToh.jbNewGame = new JButton( "New Game" );
		pnlToh.jbNewGame.setMnemonic( KeyEvent.VK_N );
		pnlToh.jbNewGame.addActionListener( pnlToh );
		pnlToh.pnlCtrls.add( pnlToh.jbNewGame, gbc );

		gbc.gridx = 1;
		pnlToh.lblNumOfDisks = new JLabel( "Number of Disks: " );
		pnlToh.pnlCtrls.add( pnlToh.lblNumOfDisks, gbc );

		gbc.gridx = 2;
		pnlToh.cmbNumOfDisks = new JComboBox( TohPanel.ALLOWED_DISK_NUMBERS );
		pnlToh.cmbNumOfDisks.setSelectedIndex( 0 );
		pnlToh.pnlCtrls.add( pnlToh.cmbNumOfDisks, gbc );

		gbc.gridx = 3;
		pnlToh.lblNumOfMoves = new JLabel( "Moves Made: " );
		pnlToh.pnlCtrls.add( pnlToh.lblNumOfMoves, gbc );

		gbc.gridx = 4;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		pnlToh.lblMovesCount = new JLabel( "0" );
		pnlToh.pnlCtrls.add( pnlToh.lblMovesCount, gbc );
	}


	TohPanel				pnlToh;

	static final Insets			INSETS = new Insets( 5, 5, 5, 5 );

	static final Color			CLRGRAY = Color.GRAY;

	private final Color			BOARD_CLR = new Color( 197, 213, 203 );
	private final Color			BASE_CLR = new Color( 227, 224, 207 );
	private final Color			TEXT_CLR = new Color( 114, 120, 116 );
}
