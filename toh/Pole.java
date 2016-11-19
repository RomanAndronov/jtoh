package toh;

/*
  By Roman Andronov
 */

class Pole
{
	Pole( int pn, PoleView pv )
	{
		myPoleNumber = pn;
		myPoleView = pv;
		myDisks = new Stack();
	}

	PoleView
	getPoleView()
	{
		return myPoleView;
	}

	int
	getPoleNumber()
	{
		return myPoleNumber;
	}

	int
	addDisk( Disk disk )
	{
		int		rv = -1;
		DiskView	dv = null;

		myDisks.push( disk );
		dv = disk.getView();
		dv.add( myPoleNumber );
		rv = myDisks.count();

		return rv;
	}

	Disk
	rmDisk()
	{
		DiskView	dv = null;
		Disk		disk = ( Disk )myDisks.pop();

		dv = disk.getView();
		dv.rm( myPoleNumber );

		return disk;
	}

	int
	topDiskNumber()
	{
		Disk		disk = null;
		int		rv = -1;
		
		if ( myDisks.count() <= 0 )
		{
			return -1;
		}
		
		disk = ( Disk )myDisks.top();
		rv = disk.getNumber();

		return rv;
	}

	int
	diskCount()
	{
		int		c = myDisks.count();

		return c;
	}

	private final int		myPoleNumber;
	private final PoleView		myPoleView;
	private Stack			myDisks = null;
}
