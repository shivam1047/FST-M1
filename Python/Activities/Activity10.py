numbers = tuple(input("Enter a sequence of comma separated values: ").split(","))
print("Elements that are divisible by 5:")
for num in numbers:
    if (int(num) % 5 == 0):
        print(num)