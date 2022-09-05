from Rectangle import Rectangle


def helloworld():
    print("Hello World")


def name():
    inputname = input("\nInsert name: ")
    print("\nHello " + inputname)


def matrix():
    list = []
    string = "WHATISTHEMATRIX"

    for i in range(0, len(string), 3):
        list.append(string[i:i+3])

    for i in range(len(list)):
        print(list[i])


def maximum(a, b):
    if a > b:
        return a
    return b


def faculty(a):
    if a == 1:
        return a
    else:
        return a * faculty(a - 1)


helloworld()

name()

print("\nMatrix:")
matrix()

print("\nMaximum [1, 3]")
print(maximum(1, 3))

print("\nFaculty [5]")
print(faculty(5))

rectangle = Rectangle(5, 10)
print("\nRectangle [5, 10]")
print("\nCircumference")
print(rectangle.calccircumference())
print("\nArea")
print(rectangle.calcarea())
