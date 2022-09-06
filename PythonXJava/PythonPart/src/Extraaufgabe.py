import timeit


def aufgabe1():
    counter = 100000000
    for i in range(25):
        counter += 100000000
        print(counter)


aufgabe1()


def time():
    return timeit.default_timer()


def output():
    for i in range(10000000):
        print(i)


def loop(runs):
    arr = [runs]
    for i in range(runs):
        starttime = time()
        output()
        endtime = time()
        arr[i-1] = endtime - starttime

    return arr


def calculatearith():
    arithmid = 0
    arithlist = loop(2)
    for number in arithlist:
        arithmid += number
    arithmid /= len(arithlist)
    return arithmid


print(calculatearith())
