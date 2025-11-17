package br.com.github.Akflamingo2610.GlobalSolutionKotlin.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.github.Akflamingo2610.GlobalSolutionKotlin.utils.calcularImc
import br.com.github.Akflamingo2610.GlobalSolutionKotlin.utils.determinarClassificacaoIMC

@Composable
fun IMCScreen(modifier: Modifier = Modifier, navController: NavController) {
    var nome by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var imc by remember { mutableStateOf(0.0) }
    var statusImc by remember { mutableStateOf("") }
    var resultadoVisivel by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(Color(0xFFED145B))
            ) {
                Text(
                    text = "Calculadora IMC",
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 64.dp, bottom = 24.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Card(
                    modifier = Modifier
                        .offset(y = (-30).dp)
                        .fillMaxWidth(),
                    colors = CardDefaults
                        .cardColors(containerColor = Color(0xfff9f6f6)),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(
                            text = "Seus dados",
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFED145B),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(32.dp))

                        Text(
                            text = "Seu nome",
                            modifier = Modifier.padding(bottom = 8.dp),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFFED145B)
                        )
                        OutlinedTextField(
                            value = nome,
                            onValueChange = { nome = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Digite seu nome")
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color(0xFFED145B),
                                focusedBorderColor = Color(0xFFED145B)
                            ),
                            shape = RoundedCornerShape(16.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Seu peso",
                            modifier = Modifier.padding(bottom = 8.dp),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFFED145B)
                        )
                        OutlinedTextField(
                            value = peso,
                            onValueChange = { peso = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Seu peso em Kg")
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color(0xFFED145B),
                                focusedBorderColor = Color(0xFFED145B)
                            ),
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Sua altura",
                            modifier = Modifier.padding(bottom = 8.dp),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFFED145B)
                        )
                        OutlinedTextField(
                            value = altura,
                            onValueChange = { altura = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Sua altura em cm")
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color(0xFFED145B),
                                focusedBorderColor = Color(0xFFED145B)
                            ),
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                                keyboardType = KeyboardType.Decimal
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                try {
                                    val pesoValue = peso.toDouble()
                                    val alturaValue = altura.toDouble()
                                    imc = calcularImc(altura = alturaValue, peso = pesoValue)
                                    statusImc = determinarClassificacaoIMC(imc)
                                    resultadoVisivel = true
                                } catch (e: Exception) {
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFED145B)
                            )
                        ) {
                            Text(
                                text = "CALCULAR",
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }

        if (resultadoVisivel) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .align(Alignment.BottomCenter),
                colors = CardDefaults.cardColors(containerColor = Color(0xff329F6B)),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Resultado",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    Text(
                        text = "$nome está com $statusImc",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 2
                    )
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    Text(
                        text = String.format("%.1f", imc),
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 42.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Button(
            onClick = { navController.navigate("menu") },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
                .offset(y = if (resultadoVisivel) (-180).dp else 0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2C4EC7)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Voltar",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

