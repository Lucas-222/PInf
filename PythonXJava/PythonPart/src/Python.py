from Rectangle import Rectangle

def matrix():
    list = []
    b = str("WHATISTHEMATRIX")
    counter = 0
    
    for i in range(int(len(b)/3)): 
        list.append(b[counter:counter+3])
        counter+=3
    return list

def maximum(a, b):
    if a > b:
        return a
    return b


def faculty(a):
    if a == 1:
        return a
    else:
        return a * faculty(a-1)


print("Hello World\n")

abfrage = input("Wie heißt du: ")
print("\nHello "+abfrage)

print("\nMatrix")
print(matrix())

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
