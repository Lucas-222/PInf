import unittest

from Polynom import Polynom


class MyTestCase(unittest.TestCase):
    def test_functionvalue_0(self):
        polynom = Polynom([3, 0, 0, 0, 0])
        self.assertEqual(3, polynom.function_value(2))

    def test_functionvalue_1(self):
        polynom = Polynom([3, 4, 0, 0, 0])
        self.assertEqual(11, polynom.function_value(2))

    def test_functionvalue_2(self):
        polynom = Polynom([10, 5, 2, 0, 0])
        self.assertEqual(28, polynom.function_value(2))

    def test_functionvalue_3(self):
        polynom = Polynom([10, 5, 2, 1, 0])
        self.assertEqual(36, polynom.function_value(2))

    def test_functionvalue_4(self):
        polynom = Polynom([10, 5, 2, 1, 7])
        self.assertEqual(148, polynom.function_value(2))

    def test_minus(self):
        polynom = Polynom([4, 5, -2, 0, 0, 8])
        self.assertEqual(6, polynom.function_value(2))


if __name__ == '__main__':
    unittest.main()
