package toh;

/*
   By Roman Andronov
*/

import javax.swing.JApplet;
import javax.swing.SwingUtilities;

public
class TohApplet
	extends JApplet
{
	public void
	init()
	{
		SwingUtilities.invokeLater( new Runnable()
		{
			public void
			run()
			{
				createAppletGui();
			}
		});
	}

	private void
	createAppletGui()
	{
		if ( tohpnl == null )
		{
			tohpnl = new TohPanel( this );
		}
	}

	private TohPanel		tohpnl = null;
}
