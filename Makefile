MF = /tmp/tohManifest

TOH = toh.jar
SRCDIR = toh

JFLAGS = -g
JAVAC = javac -cp ./$(SRCDIR):${CLASSPATH}

.SUFFIXES: .java .class
.java.class:
	$(JAVAC) $(JFLAGS) $<

_TOH_SRC = TohApplet.java \
	TohFrame.java \
	TohPanel.java \
	TohGui.java \
	Disk.java \
	DiskView.java \
	Pole.java \
	PoleView.java \
	Stack.java

TOH_SRC = $(_TOH_SRC:%=$(SRCDIR)/%)

TOH_CLASSES = $(TOH_SRC:.java=.class)

$(TOH):	$(TOH_SRC) $(TOH_CLASSES)
	rm -f $(MF)
	echo "Main-Class: $(SRCDIR)/TohFrame" > $(MF)
	jar cmf $(MF) $@ $(SRCDIR)/*.class
	rm -f $(MF)

clean:
	rm -f $(TOH) $(SRCDIR)/*.class
