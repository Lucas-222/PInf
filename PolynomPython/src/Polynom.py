class Polynom:

    def __init__(self, coefficients):
        self.coefficients = coefficients
        self.SYMMETRIES = ["axisymmetric", "pointsymmetric", "not symmetric"]
        self.exception = ""
        self.symmetry = ""
        self.degree = 0

    def __str__(self):
        output = ""
        for c in self.coefficients:
            if c != 0:
                print("1")
                output += c

        return output


























class Test:

    def test_inpup_1(self):
        polynom = Polynom([5, 0, 0, 0, 0])
        print(polynom.coefficients)
        if polynom.__str__() == "5":
            print("true")
        else:
            print("false")




if __name__ == '__main__':
    test = Test()
    test.test_inpup_1()

