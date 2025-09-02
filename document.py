print("hello world")
espada = 25;
vida=50;

print("ingrese su nombre")
nombre= input("")
print(f"{nombre}, usted tiene una vida de {vida}")
    
def mostrar_menu():
    print("juegaso")
    print("1. Ver información de la Espada de Fuego")
    print("2. Atacar a un enemigo")
    print("3. Salir")

def infoEspada():
    print(f" La espada de fuego tiene un daño de {espada} puntos.")
    machetazo = vida - espada
    print(f"{nombre}, te cortaste accidentalmente y te quedan {machetazo} puntos de vida.")

def atacarEmemigo():
    print("atacastea a un enemigo de la nada ")

def juego():
    
    mostrar_menu()
opciones = input("escribe un numero")
if opciones == "1":
    infoEspada()
elif opciones == "2":
    tacar_enemigo()

else:
            print("Opción no válida. Intenta de nuevo.")


juego()