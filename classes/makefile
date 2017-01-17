JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
		Hero.java \
		Monster.java \
		Mob.java \
		Sword.java \
		Healer.java \
		Shop.java \
		SwordName.java \
		TownName.java \
		MonsterName.java \
		Location.java \
		Map.java \
		Game.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
		$(RM) *.class