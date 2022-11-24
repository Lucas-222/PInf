def own_exponential_because_python_sucks(x, y):
    product = 1
    for i in range(y):
        product *= x
    return product


class Polynom:

    def __init__(self, coefficients):
        self.SYMMETRIES = ["axisymmetric", "pointsymmetric", "not symmetric"]
        self.exception = ""
        self.coefficients = [(coefficients[0], 0), (coefficients[1], 1),
                             (coefficients[2], 2), (coefficients[3], 3),
                             (coefficients[4], 4)]
        self.degree = self.initialise_degree()
        self.symmetry = self.initialise_symmetry()

    def get_degree(self):
        return self.degree

    def get_symmetry(self):
        return self.symmetry

    def initialise_degree(self):
        for c in self.coefficients[::-1]:
            if c[0] != 0:
                return c[1]

    def initialise_symmetry(self):
        total_numbers = 0
        even_numbers = 0
        odd_numbers = 0

        # Count even, odd and total numbers
        for c in self.coefficients:
            if c[0] != 0:
                total_numbers += 1
                if c[1] % 2 == 0:
                    even_numbers += 1
                else:
                    odd_numbers += 1

        if total_numbers == even_numbers:
            return self.SYMMETRIES[0]
        elif total_numbers == odd_numbers:
            return self.SYMMETRIES[1]
        return self.SYMMETRIES[2]

    def function_value(self, x):
        if self.exception == "":
            return sum((v[0] * own_exponential_because_python_sucks(x, v[1])) for v in self.coefficients)

    def __str__(self):
        if self.exception != "":
            return self.exception

        # Reverse the coefficients
        temp = self.coefficients
        first_value_index = 0
        last_value_index = -1
        for i in range(4, -1, -1):
            if temp[i][0] != 0:
                first_value_index = temp[i][1]
                if last_value_index == -1:
                    last_value_index = temp[i][1]

        builder = "f(x) = "
        for i in range(4, -1, -1):
            # If value is not 0
            if temp[i][0] != 0:
                # If number is negative
                if (temp[i][0] < 0) & (i != last_value_index):
                    builder += str(temp[i][0] * -1)
                else:
                    builder += str(temp[i][0])
                if i != 0:
                    builder += "x"
                    if i != 1:
                        builder += "^" + str(i)

                    if i > first_value_index:
                        if temp[i - 1][0] < 0:
                            builder += " - "
                        else:
                            builder += " + "
        return builder
