package pe.edu.cibertec.pizzashop

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.edu.cibertec.pizzashop.ui.theme.PizzaShopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PizzaShopTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PizzaMenu()
                }
            }
        }
    }
}

@Composable
fun PizzaMenu(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    val pizzaSizes = listOf("Pequeña (5$)", "Mediana (7$)", "Grande (9$)")

    val pizzaPrices = listOf(5, 7, 9)

    val selectedPizzaSize = remember {
        mutableStateOf(-1)
    }
    val isCheckedCebollas = remember {
        mutableStateOf(false)
    }
    val isCheckedAceitunas = remember {
        mutableStateOf(false)
    }
    val isCheckedTomates = remember {
        mutableStateOf(false)
    }
    val totalPrice = remember {
        mutableStateOf(0)
    }

    Column(
        modifier = modifier.padding(8.dp),
        ) {
        Text(
            text = "Pizza Shop",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Spacer(modifier = modifier.height(8.dp))

        Text(
            text = "Escoja su tamaño"
        )

        Spacer(modifier = modifier.padding(vertical = 4.dp))

        //Elaboración de los RadioButton
        pizzaSizes.forEachIndexed { i, pizzaSize ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedPizzaSize.value == i,
                    onClick = {
                        selectedPizzaSize.value = i
                    }
                )
                Text(text = pizzaSize)
            }
        }

        Text(
            text = "Escoja sus adicionales"
        )

        Spacer(modifier = modifier.padding(vertical =  4.dp))

        //Elaboración de los CheckBox

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isCheckedCebollas.value,
                onCheckedChange = {
                    isCheckedCebollas.value = it
                })
            Text(
                text ="Cebollas (1$)"
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isCheckedAceitunas.value,
                onCheckedChange = {
                    isCheckedAceitunas.value = it
                })
            Text(
                text ="Aceitunas (2$)"
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isCheckedTomates.value,
                onCheckedChange = {
                    isCheckedTomates.value = it
                })
            Text(
                text ="Tomates (3$)"
            )
        }

        //Elaboración del Button para la confirmación del pedido
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                var price = pizzaPrices[selectedPizzaSize.value]
                if (isCheckedCebollas.value) price +=1
                if (isCheckedAceitunas.value) price += 2
                if (isCheckedTomates.value) price += 3

                totalPrice.value = price
                Toast.makeText(context, "Su pedido vale ${totalPrice.value}$",Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "Confirmar Pedido")
            }
        }

        Spacer(modifier = modifier.padding(vertical =  8.dp))

        Text(text = "Precio total: ${totalPrice.value}$")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PizzaShopTheme {
        PizzaMenu()
    }
}