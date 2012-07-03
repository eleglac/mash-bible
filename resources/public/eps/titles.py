from HTMLParser import HTMLParser

titles = []

class fuckinHTMLparser(HTMLParser):    
    tagFlag = False
    title   = ""

    def handle_starttag(self, tag, attrs):
        print "Start tag:", tag
        if (tag == "strong"):
            self.tagFlag = True
            print "TRIGGER'D!"

    def handle_endtag(self, tag):
        print "End tag  :", tag
        if (tag == "strong"):
            self.tagFlag = False
            print "detrigger'd D:"

    def handle_data(self, data):
        print "Data     :", data
        if (self.tagFlag and (data != "NOTE:")):
            self.title = data

parser = fuckinHTMLparser()
titleline = ""

for i in range(1, 12):
    t = open(str(i) + ".txt", "w")
    print str(i)

    for j in range (1, 26):
        try:
            fname = str(i) + " x " + str(j) + ".html"
            print fname
            f = open(fname, 'r')
            parser.feed(f.read())
            print parser.title
            t.write(parser.title + "\n")
            #parser.title = ""
            #break
        except Exception:
            print "You dun goofed." + str(i) + str(j)
