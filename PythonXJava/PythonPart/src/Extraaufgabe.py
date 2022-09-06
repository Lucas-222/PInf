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
    arr = []
    for i in range(runs):
        starttime = time()
        output(length)
        endtime = time()
        arr.append(endtime - starttime)

    return arr


def calculatearith():
    arithmid = 0
    arithlist = loop(3, 10000000)

    for i in range(len(arithlist)):
        arithmid += arithlist[i]
        print("Run [" + str(i + 1) + "]: " + str(arithlist[i]) + "s")

    return arithmid / len(arithlist)


print("Arithm: " + str(calculatearith()) + "s (Python)")
