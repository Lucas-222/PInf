from Rectangle import Rectangle


def helloworld():
    print("Hello World")


helloworld()


def name():
    inputname = input("\nInsert name: ")
    print("\nHello " + inputname)


name()


def matrix():
    list = []
    string = "WHATISTHEMATRIX"

    for i in range(0, len(string), 3):
        list.append(string[i:i + 3])

    for i in range(len(list)):
        print(list[i])


print("\nMatrix:")
matrix()


def maximum(a, b):
    if a > b:
        return a
    return b


print("\nMaximum [1, 3]")
print(maximum(1, 3))


def faculty(a):
    if a == 1:
        return a
    else:
        return a * faculty(a - 1)


print("\nFaculty [5]")
print(faculty(5))


# Rectangle
rectangle = Rectangle(5, 10)
print("\nRectangle [5, 10]")
print("\nCircumference")
print(rectangle.calccircumference())
print("\nArea")
print(rectangle.calcarea())
