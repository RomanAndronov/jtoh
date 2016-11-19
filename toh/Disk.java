package toh;

/*
  By Roman Andronov
 */

class Disk
{
	Disk( int dn, DiskView dv )
	{
		myNumber = dn;
		myView = dv;
	}

	DiskView
	getView()
	{
		return myView;
	}

	int
	getNumber()
	{
		return myNumber;
	}

	private final int		myNumber;
	private final DiskView		myView;
}
