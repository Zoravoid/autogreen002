from math import e

def main():
    irf = 1 / (1 + e**(1.2 * (0 - 0)))
    print(irf)

if __name__ == "__main__":
    main()