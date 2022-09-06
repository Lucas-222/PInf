import timeit


def aufgabe1():
    counter = 100000000
    for i in range(25):
        counter += 100000000
        print(counter)


aufgabe1()


def time():
    return timeit.default_timer()


def output(length):
    for i in range(length):
        print(i)


def loop(runs, length):
    arr = [runs]
    for i in range(runs):
        starttime = time()
        output(length)
        endtime = time()
        arr[i-1] = endtime - starttime

    return arr


def calculatearith():
    arithmid = 0
    arithlist = loop(1, 10000000)
    for number in arithlist:
        arithmid += number
    return arithmid / len(arithlist)


print(str(calculatearith()) + "s (Python)")
