class Rectangle:
    def __init__(self, length, width):
        self.__length = length
        self.__width = width

    def setlength(self, length):
        self.__length = length

    def getlength(self):
        return self.__length

    def setwidth(self, width):
        self.__width = width

    def getwidth(self):
        return self.__width

    def calccircumference(self):
        return self.__length*2+self.__width*2

    def calcarea(self):
        return self.__length*self.__width
