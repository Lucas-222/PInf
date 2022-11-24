import unittest

from Polynom import Polynom


class MyTestCase(unittest.TestCase):
    def test_input_1(self):
        polynom = Polynom([5, 0, 0, 0, 0])
        self.assertEqual("f(x) = 5", polynom.__str__())

    def test_input_2(self):
        polynom = Polynom([5, 2, 0, 0, 0])
        self.assertEqual("f(x) = 2x + 5", polynom.__str__())

    def test_input_3(self):
        polynom = Polynom([5, 3, 4, 0, 0])
        self.assertEqual("f(x) = 4x^2 + 3x + 5", polynom.__str__())

    def test_input_4(self):
        polynom = Polynom([5, 1, 5, 6, 0])
        self.assertEqual("f(x) = 6x^3 + 5x^2 + 1x + 5", polynom.__str__())

    def test_input_5(self):
        polynom = Polynom([5, 2, 8, 9, 10])
        self.assertEqual("f(x) = 10x^4 + 9x^3 + 8x^2 + 2x + 5", polynom.__str__())

    def test_minus(self):
        polynom = Polynom([5, -2, 8, 9, 10])
        self.assertEqual("f(x) = 10x^4 + 9x^3 + 8x^2 - 2x + 5", polynom.__str__())

    def test_minus_at_the_start(self):
        polynom = Polynom([-5, 2, 8, 9, -10])
        self.assertEqual("f(x) = -10x^4 + 9x^3 + 8x^2 + 2x - 5", polynom.__str__())

    def test_holes_1(self):
        polynom = Polynom([0, 1, -2, 0, 0])
        self.assertEqual("f(x) = -2x^2 + 1x", polynom.__str__())

    def test_holes_2(self):
        polynom = Polynom([1, 0, -2, 0, 0])
        self.assertEqual("f(x) = -2x^2 + 1", polynom.__str__())

    def test_degree(self):
        polynom = Polynom([1, 0, 2, 0, 0])
        self.assertEqual(2, polynom.get_degree())

    def test_pointsymmetry(self):
        polynom = Polynom([0, 1, 0, 4, 0])
        self.assertEqual(polynom.SYMMETRIES[1], polynom.get_symmetry())

    def test_axissymmetry(self):
        polynom = Polynom([1, 0, 2, 0, 0])
        self.assertEqual(polynom.SYMMETRIES[0], polynom.get_symmetry())

    def test_no_symmetry(self):
        polynom = Polynom([1, 4, 2, 0, 0])
        self.assertEqual(polynom.SYMMETRIES[2], polynom.get_symmetry())


if __name__ == '__main__':
    unittest.main()
