from Rectangle import Rectangle

print("Hello World")


def name():
    inputname = input("\nInsert name: ")
    print("\nHello " + inputname)


name()


def matrix():
    list = []
    string = "WHATISTHEMATRIX"

    for i in range(0, len(string), 3):
        list.append(string[i:i + 3])

    for s in list:
        print(s)


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

rectangle = Rectangle(5, 10)
print(rectangle.calccircumference())
print(rectangle.calcarea())
