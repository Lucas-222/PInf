class Rectangle:
    def __init__(self, length, width):
        self.length = length
        self.width = width

    def setlength(self, length):
        self.length = length

    def getlength(self):
        return self.length

    def setwidth(self, width):
        self.width = width

    def getwidth(self):
        return self.width

    def calccircumference(self):
        return self.length*2+self.width*2

    def calcarea(self):
        return self.length*self.width