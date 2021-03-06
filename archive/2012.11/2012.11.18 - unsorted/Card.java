package chelper;

import abrackadabra.io.InputReader;

import java.io.PrintWriter;
import java.math.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Card {
    AtomicInteger a = new AtomicInteger(0);

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int[] ans = new int[1001];

        ans[1] = 0;
        ans[3] = 0;
        ans[2] = 0;
        ans[5] = 0;
        ans[13] = 2;
        ans[12] = 2;
        ans[4] = 0;
        ans[18] = 2;
        ans[8] = 1;
        ans[15] = 1;
        ans[16] = 0;
        ans[11] = 0;
        ans[6] = 0;
        ans[10] = 1;
        ans[9] = 0;
        ans[17] = 3;
        ans[7] = 1;
        ans[14] = 0;
        ans[19] = 3;
        ans[20] = 2;
        ans[22] = 3;
        ans[28] = 5;
        ans[21] = 1;
        ans[32] = 4;
        ans[24] = 4;
        ans[56] = 6;
        ans[26] = 1;
        ans[30] = 2;
        ans[23] = 2;
        ans[31] = 3;
        ans[25] = 0;
        ans[35] = 1;
        ans[34] = 3;
        ans[39] = 4;
        ans[33] = 5;
        ans[29] = 3;
        ans[43] = 4;
        ans[47] = 4;
        ans[51] = 3;
        ans[55] = 5;
        ans[37] = 5;
        ans[49] = 0;
        ans[45] = 3;
        ans[41] = 4;
        ans[57] = 6;
        ans[53] = 5;
        ans[40] = 6;
        ans[36] = 0;
        ans[38] = 3;
        ans[42] = 4;
        ans[48] = 7;
        ans[61] = 9;
        ans[46] = 4;
        ans[50] = 4;
        ans[64] = 0;
        ans[58] = 6;
        ans[76] = 7;
        ans[68] = 5;
        ans[72] = 9;
        ans[84] = 8;
        ans[80] = 7;
        ans[92] = 9;
        ans[100] = 0;
        ans[108] = 12;
        ans[88] = 8;
        ans[54] = 5;
        ans[60] = 5;
        ans[104] = 11;
        ans[124] = 15;
        ans[112] = 12;
        ans[128] = 12;
        ans[116] = 12;
        ans[132] = 13;
        ans[44] = 4;
        ans[140] = 9;
        ans[144] = 0;
        ans[136] = 13;
        ans[148] = 13;
        ans[152] = 13;
        ans[52] = 8;
        ans[120] = 10;
        ans[156] = 15;
        ans[164] = 12;
        ans[168] = 14;
        ans[172] = 18;
        ans[192] = 17;
        ans[200] = 18;
        ans[204] = 17;
        ans[196] = 0;
        ans[70] = 4;
        ans[62] = 5;
        ans[66] = 4;
        ans[74] = 6;
        ans[86] = 7;
        ans[216] = 11;
        ans[82] = 7;
        ans[78] = 8;
        ans[208] = 17;
        ans[224] = 20;
        ans[90] = 7;
        ans[220] = 20;
        ans[212] = 19;
        ans[232] = 22;
        ans[102] = 10;
        ans[94] = 5;
        ans[98] = 10;
        ans[110] = 6;
        ans[236] = 14;
        ans[228] = 20;
        ans[106] = 11;
        ans[114] = 11;
        ans[256] = 0;
        ans[244] = 20;
        ans[134] = 10;
        ans[529] = 0;
        ans[118] = 12;
        ans[122] = 9;
        ans[248] = 19;
        ans[166] = 15;
        ans[126] = 11;
        ans[252] = 17;
        ans[162] = 15;
        ans[154] = 13;
        ans[260] = 21;
        ans[409] = 30;
        ans[146] = 9;
        ans[130] = 12;
        ans[264] = 21;
        ans[150] = 12;
        ans[142] = 9;
        ans[138] = 12;
        ans[158] = 13;
        ans[292] = 30;
        ans[170] = 11;
        ans[272] = 24;
        ans[268] = 27;
        ans[186] = 10;
        ans[284] = 25;
        ans[300] = 25;
        ans[178] = 11;
        ans[174] = 10;
        ans[296] = 30;
        ans[182] = 11;
        ans[190] = 16;
        ans[312] = 26;
        ans[198] = 14;
        ans[324] = 0;
        ans[288] = 25;
        ans[202] = 14;
        ans[194] = 11;
        ans[316] = 27;
        ans[304] = 23;
        ans[206] = 11;
        ans[332] = 25;
        ans[336] = 24;
        ans[320] = 28;
        ans[214] = 19;
        ans[222] = 13;
        ans[218] = 18;
        ans[340] = 19;
        ans[328] = 28;
        ans[230] = 9;
        ans[226] = 20;
        ans[344] = 27;
        ans[352] = 30;
        ans[210] = 15;
        ans[348] = 23;
        ans[356] = 24;
        ans[234] = 21;
        ans[238] = 20;
        ans[246] = 18;
        ans[360] = 29;
        ans[242] = 19;
        ans[364] = 29;
        ans[254] = 17;
        ans[262] = 19;
        ans[270] = 24;
        ans[368] = 30;
        ans[396] = 30;
        ans[392] = 34;
        ans[278] = 20;
        ans[274] = 20;
        ans[282] = 14;
        ans[290] = 17;
        ans[286] = 22;
        ans[412] = 30;
        ans[294] = 21;
        ans[298] = 22;
        ans[302] = 24;
        ans[306] = 22;
        ans[314] = 18;
        ans[424] = 36;
        ans[322] = 25;
        ans[334] = 28;
        ans[350] = 26;
        ans[346] = 22;
        ans[362] = 30;
        ans[354] = 24;
        ans[374] = 17;
        ans[386] = 32;
        ans[370] = 26;
        ans[394] = 25;
        ans[476] = 34;
        ans[434] = 22;
        ans[450] = 29;
        ans[59] = 4;
        ans[119] = 7;
        ans[107] = 9;
        ans[111] = 7;
        ans[458] = 33;
        ans[131] = 7;
        ans[103] = 8;
        ans[127] = 13;
        ans[99] = 8;
        ans[91] = 8;
        ans[139] = 13;
        ans[147] = 13;
        ans[79] = 10;
        ans[75] = 5;
        ans[143] = 12;
        ans[83] = 5;
        ans[67] = 9;
        ans[87] = 8;
        ans[187] = 14;
        ans[191] = 14;
        ans[446] = 29;
        ans[71] = 7;
        ans[95] = 6;
        ans[63] = 8;
        ans[199] = 17;
        ans[163] = 14;
        ans[159] = 11;
        ans[195] = 13;
        ans[175] = 15;
        ans[183] = 12;
        ans[474] = 32;
        ans[203] = 16;
        ans[167] = 14;
        ans[179] = 13;
        ans[171] = 15;
        ans[151] = 13;
        ans[155] = 9;
        ans[135] = 13;
        ans[115] = 14;
        ans[123] = 9;
        ans[215] = 12;
        ans[223] = 14;
        ans[219] = 18;
        ans[211] = 17;
        ans[490] = 34;
        ans[482] = 30;
        ans[227] = 21;
        ans[207] = 20;
        ans[235] = 21;
        ans[251] = 15;
        ans[247] = 21;
        ans[239] = 16;
        ans[243] = 20;
        ans[259] = 18;
        ans[263] = 15;
        ans[255] = 16;
        ans[271] = 24;
        ans[283] = 24;
        ans[291] = 21;
        ans[315] = 23;
        ans[323] = 26;
        ans[327] = 27;
        ans[307] = 21;
        ans[355] = 24;
        ans[383] = 28;
        ans[367] = 27;
        ans[395] = 20;
        ans[407] = 26;
        ans[431] = 27;
        ans[69] = 7;
        ans[65] = 6;
        ans[73] = 10;
        ans[471] = 36;
        ans[27] = 2;
        ans[77] = 5;
        ans[97] = 9;
        ans[503] = 35;
        ans[89] = 10;
        ans[81] = 0;
        ans[85] = 8;
        ans[93] = 7;
        ans[109] = 11;
        ans[113] = 14;
        ans[129] = 13;
        ans[117] = 11;
        ans[121] = 0;
        ans[137] = 11;
        ans[101] = 9;
        ans[105] = 9;
        ans[145] = 13;
        ans[141] = 13;
        ans[125] = 7;
        ans[133] = 14;
        ans[149] = 13;
        ans[157] = 18;
        ans[153] = 12;
        ans[165] = 15;
        ans[169] = 0;
        ans[173] = 14;
        ans[185] = 17;
        ans[177] = 16;
        ans[193] = 16;
        ans[181] = 21;
        ans[189] = 15;
        ans[197] = 14;
        ans[201] = 18;
        ans[217] = 17;
        ans[209] = 16;
        ans[205] = 24;
        ans[213] = 15;
        ans[221] = 15;
        ans[237] = 16;
        ans[229] = 22;
        ans[261] = 15;
        ans[241] = 22;
        ans[225] = 0;
        ans[249] = 17;
        ans[233] = 24;
        ans[245] = 21;
        ans[257] = 24;
        ans[253] = 22;
        ans[265] = 20;
        ans[289] = 0;
        ans[269] = 23;
        ans[285] = 19;
        ans[281] = 24;
        ans[297] = 25;
        ans[273] = 17;
        ans[317] = 20;
        ans[321] = 27;
        ans[361] = 0;
        ans[341] = 22;
        ans[357] = 23;
        ans[365] = 24;
        ans[369] = 28;
        ans[353] = 27;
        ans[333] = 23;
        ans[345] = 32;
        ans[277] = 29;
        ans[313] = 25;
        ans[161] = 13;
        ans[293] = 24;
        ans[524] = 38;
        ans[531] = 38;
        ans[518] = 33;
        ans[459] = 28;
        ans[510] = 30;
        ans[423] = 31;
        ans[435] = 32;
        ans[467] = 31;
        ans[371] = 26;
        ans[399] = 30;
        ans[335] = 14;
        ans[347] = 23;
        ans[311] = 22;
        ans[287] = 20;
        ans[279] = 21;
        ans[363] = 32;
        ans[275] = 20;
        ans[504] = 32;
        ans[267] = 16;
        ans[470] = 32;
        ans[382] = 29;
        ans[398] = 34;
        ans[358] = 24;
        ans[390] = 25;
        ans[418] = 30;
        ans[330] = 19;
        ans[484] = 0;
        ans[420] = 37;
        ans[432] = 32;
        ans[326] = 17;
        ans[318] = 24;
        ans[488] = 35;
        ans[416] = 33;
        ans[258] = 20;
        ans[266] = 15;
        ans[456] = 34;
        ans[250] = 24;
        ans[276] = 27;
        ans[280] = 28;
        ans[436] = 30;
        ans[180] = 18;
        ans[188] = 13;
        ans[376] = 33;
        ans[184] = 18;
        ans[388] = 35;
        ans[160] = 13;
        ans[389] = 29;
        ans[96] = 9;
        ans[231] = 18;
        ans[331] = 20;
        ans[441] = 0;
        ans[240] = 20;
        ans[422] = 27;
        ans[405] = 33;
        ans[419] = 22;
        ans[443] = 38;
        ans[556] = 45;
        ans[451] = 31;
        ans[457] = 34;
        ans[433] = 33;
        ans[176] = 12;
        ans[445] = 37;
        ans[408] = 29;
        ans[400] = 0;
        ans[308] = 23;
        ans[310] = 21;
        ans[380] = 27;
        ans[342] = 23;
        ans[338] = 27;
        ans[426] = 26;
        ans[366] = 30;
        ans[406] = 26;
        ans[430] = 30;
        ans[295] = 23;
        ans[498] = 39;
        ans[299] = 17;
        ans[343] = 16;
        ans[319] = 27;
        ans[303] = 22;
        ans[387] = 31;
        ans[351] = 26;
        ans[439] = 33;
        ans[411] = 24;
        ans[375] = 27;
        ans[379] = 32;
        ans[447] = 32;
        ans[483] = 33;
        ans[542] = 33;
        ans[487] = 39;
        ans[663] = 35;
        ans[550] = 32;
        ans[377] = 23;
        ans[325] = 21;
        ans[305] = 26;
        ans[301] = 24;
        ans[576] = 0;
        ans[337] = 27;
        ans[309] = 25;
        ans[329] = 29;
        ans[603] = 43;
        ans[560] = 41;
        ans[596] = 41;
        ans[566] = 31;
        ans[784] = 0;
        ans[385] = 31;
        ans[562] = 39;
        ans[628] = 48;
        ans[588] = 39;
        ans[381] = 26;
        ans[579] = 36;
        ans[511] = 35;
        ans[554] = 30;
        ans[514] = 39;
        ans[491] = 33;
        ans[500] = 33;
        ans[391] = 34;
        ans[502] = 33;
        ans[403] = 26;
        ans[463] = 36;
        ans[339] = 24;
        ans[359] = 22;
        ans[438] = 33;
        ans[460] = 31;
        ans[402] = 28;
        ans[448] = 32;
        ans[444] = 35;
        ans[414] = 30;
        ans[454] = 27;
        ans[440] = 23;
        ans[417] = 31;
        ans[384] = 32;
        ans[401] = 32;
        ans[397] = 34;
        ans[413] = 25;
        ans[461] = 32;
        ans[481] = 34;
        ans[729] = 0;
        ans[477] = 33;
        ans[453] = 31;
        ans[594] = 40;
        ans[841] = 0;
        ans[489] = 37;
        ans[718] = 52;
        ans[585] = 43;
        ans[493] = 39;
        ans[541] = 42;
        ans[429] = 33;
        ans[521] = 39;
        ans[557] = 35;
        ans[428] = 28;
        ans[372] = 27;
        ans[404] = 34;
        ans[425] = 31;
        ans[462] = 30;
        ans[478] = 38;
        ans[378] = 31;
        ans[437] = 32;
        ans[464] = 30;
        ans[494] = 25;
        ans[508] = 44;
        ans[607] = 41;
        ans[415] = 31;
        ans[696] = 51;
        ans[519] = 32;
        ans[495] = 34;
        ans[523] = 35;
        ans[632] = 44;
        ans[532] = 39;
        ans[515] = 29;
        ans[499] = 32;
        ans[546] = 32;
        ans[643] = 42;
        ans[574] = 40;
        ans[555] = 41;
        ans[598] = 40;
        ans[578] = 38;
        ans[349] = 28;
        ans[572] = 35;
        ans[535] = 38;
        ans[544] = 40;
        ans[559] = 44;
        ans[530] = 25;
        ans[611] = 33;
        ans[538] = 34;
        ans[479] = 30;
        ans[427] = 33;
        ans[506] = 32;
        ans[575] = 43;
        ans[468] = 37;
        ans[410] = 30;
        ans[466] = 41;
        ans[452] = 30;
        ans[486] = 36;
        ans[630] = 42;
        ans[442] = 30;
        ans[533] = 29;
        ans[569] = 41;
        ans[728] = 43;
        ans[609] = 42;
        ans[517] = 39;
        ans[616] = 38;
        ans[617] = 52;
        ans[373] = 32;
        ans[666] = 48;
        ans[526] = 38;
        ans[659] = 39;
        ans[683] = 37;
        ans[581] = 44;
        ans[475] = 40;
        ans[536] = 36;
        ans[648] = 46;
        ans[639] = 42;
        ans[699] = 39;
        ans[591] = 37;
        ans[583] = 38;
        ans[586] = 40;
        ans[610] = 43;
        ans[634] = 48;
        ans[590] = 35;
        ans[673] = 42;
        ans[726] = 46;
        ans[473] = 34;
        ans[605] = 42;
        ans[480] = 35;
        ans[497] = 38;
        ans[537] = 36;
        ans[492] = 34;
        ans[672] = 49;
        ans[618] = 37;
        ans[522] = 38;
        ans[507] = 33;
        ans[455] = 23;
        ans[612] = 40;
        ans[671] = 38;
        ans[520] = 39;
        ans[681] = 52;
        ans[676] = 0;
        ans[547] = 36;
        ans[595] = 40;
        ans[551] = 31;
        ans[627] = 35;
        ans[563] = 34;
        ans[798] = 42;
        ans[651] = 42;
        ans[623] = 42;
        ans[548] = 38;
        ans[552] = 34;
        ans[568] = 46;
        ans[587] = 36;
        ans[539] = 38;
        ans[465] = 35;
        ans[564] = 40;
        ans[485] = 30;
        ans[801] = 53;
        ans[615] = 36;
        ans[619] = 43;
        ans[682] = 41;
        ans[608] = 45;
        ans[614] = 39;
        ans[602] = 32;
        ans[636] = 49;
        ans[657] = 47;
        ans[449] = 33;
        ans[593] = 42;
        ans[777] = 49;
        ans[573] = 36;
        ans[670] = 44;
        ans[512] = 25;
        ans[540] = 44;
        ans[509] = 38;
        ans[505] = 35;
        ans[513] = 43;
        ans[625] = 0;
        ans[582] = 31;
        ans[567] = 37;
        ans[501] = 36;
        ans[549] = 40;
        ans[525] = 37;
        ans[545] = 40;
        ans[692] = 46;
        ans[735] = 48;
        ans[695] = 34;
        ans[739] = 49;
        ans[807] = 50;
        ans[736] = 48;
        ans[705] = 53;
        ans[580] = 45;
        ans[715] = 49;
        ans[854] = 43;
        ans[872] = 58;
        ans[656] = 49;
        ans[871] = 56;
        ans[710] = 43;
        ans[746] = 47;
        ans[788] = 51;
        ans[826] = 52;
        ans[779] = 45;
        ans[667] = 51;
        ans[888] = 49;
        ans[764] = 48;
        ans[778] = 47;
        ans[688] = 51;
        ans[804] = 47;
        ans[752] = 52;
        ans[762] = 48;
        ans[834] = 58;
        ans[742] = 48;
        ans[653] = 36;
        ans[734] = 36;
        ans[765] = 53;
        ans[749] = 50;
        ans[706] = 50;
        ans[701] = 48;
        ans[769] = 44;
        ans[743] = 46;
        ans[685] = 54;
        ans[727] = 47;
        ans[767] = 45;
        ans[703] = 47;
        ans[624] = 45;
        ans[689] = 51;
        ans[708] = 47;
        ans[664] = 47;
        ans[774] = 60;
        ans[712] = 49;
        ans[799] = 61;
        ans[626] = 40;
        ans[694] = 54;
        ans[597] = 41;
        ans[679] = 44;
        ans[815] = 51;
        ans[606] = 43;
        ans[714] = 46;
        ans[698] = 42;
        ans[553] = 44;
        ans[814] = 53;
        ans[991] = 63;
        ans[720] = 49;
        ans[750] = 46;
        ans[800] = 56;
        ans[722] = 51;
        ans[678] = 51;
        ans[650] = 40;
        ans[649] = 41;
        ans[633] = 43;
        ans[613] = 48;
        ans[707] = 40;
        ans[773] = 53;
        ans[693] = 49;
        ans[665] = 51;
        ans[836] = 53;
        ans[797] = 53;
        ans[759] = 45;
        ans[686] = 47;
        ans[748] = 54;
        ans[527] = 37;
        ans[755] = 42;
        ans[725] = 45;
        ans[713] = 43;
        ans[604] = 44;
        ans[655] = 43;
        ans[675] = 40;
        ans[516] = 39;
        ans[900] = 0;
        ans[787] = 53;
        ans[647] = 38;
        ans[837] = 54;
        ans[730] = 52;
        ans[846] = 48;
        ans[700] = 40;
        ans[732] = 45;
        ans[662] = 43;
        ans[816] = 48;
        ans[571] = 44;
        ans[642] = 42;
        ans[629] = 41;
        ans[641] = 48;
        ans[796] = 51;
        ans[601] = 44;
        ans[751] = 53;
        ans[599] = 41;
        ans[565] = 47;
        ans[561] = 43;
        ans[961] = 0;
        ans[645] = 40;
        ans[621] = 41;
        ans[724] = 46;
        ans[697] = 48;
        ans[646] = 43;
        ans[677] = 44;
        ans[738] = 53;
        ans[719] = 41;
        ans[716] = 55;
        ans[577] = 45;
        ans[702] = 48;
        ans[658] = 45;
        ans[600] = 41;
        ans[654] = 42;
        ans[496] = 42;
        ans[937] = 65;
        ans[584] = 43;
        ans[660] = 42;
        ans[543] = 39;
        ans[640] = 45;
        ans[723] = 36;
        ans[635] = 42;
        ans[592] = 49;
        ans[674] = 41;
        ans[652] = 51;
        ans[631] = 43;
        ans[740] = 49;
        ans[534] = 39;
        ans[684] = 56;
        ans[472] = 36;
        ans[794] = 43;
        ans[528] = 38;
        ans[668] = 46;
        ans[644] = 48;
        ans[622] = 43;
        ans[638] = 45;
        ans[680] = 48;
        ans[849] = 55;
        ans[589] = 43;
        ans[393] = 31;
        ans[669] = 44;
        ans[927] = 65;
        ans[754] = 50;
        ans[731] = 42;
        ans[620] = 45;
        ans[558] = 37;
        ans[964] = 64;
        ans[469] = 37;
        ans[421] = 31;
        ans[570] = 33;
        ans[828] = 53;
        ans[970] = 64;
        ans[949] = 63;
        ans[881] = 63;
        ans[893] = 48;
        ans[978] = 57;
        ans[886] = 54;
        ans[921] = 70;
        ans[845] = 62;
        ans[943] = 56;
        ans[866] = 46;
        ans[963] = 65;
        ans[833] = 51;
        ans[931] = 61;
        ans[892] = 58;
        ans[822] = 54;
        ans[737] = 56;
        ans[981] = 62;
        ans[856] = 60;
        ans[911] = 55;
        ans[924] = 66;
        ans[862] = 52;
        ans[776] = 51;
        ans[806] = 51;
        ans[998] = 56;
        ans[809] = 59;
        ans[979] = 55;
        ans[910] = 61;
        ans[852] = 57;
        ans[855] = 59;
        ans[891] = 61;
        ans[967] = 61;
        ans[898] = 59;
        ans[896] = 61;
        ans[869] = 56;
        ans[971] = 50;
        ans[783] = 52;
        ans[867] = 57;
        ans[912] = 63;
        ans[958] = 57;
        ans[1000] = 35;
        ans[721] = 51;
        ans[990] = 58;
        ans[812] = 49;
        ans[894] = 50;
        ans[889] = 65;
        ans[897] = 59;
        ans[887] = 59;
        ans[838] = 53;
        ans[863] = 48;
        ans[772] = 57;
        ans[844] = 58;
        ans[858] = 51;
        ans[905] = 57;
        ans[954] = 60;
        ans[926] = 51;
        ans[853] = 50;
        ans[976] = 68;
        ans[942] = 56;
        ans[941] = 54;
        ans[908] = 63;
        ans[946] = 59;
        ans[805] = 59;
        ans[840] = 56;
        ans[885] = 52;
        ans[915] = 59;
        ans[813] = 51;
        ans[955] = 60;
        ans[851] = 48;
        ans[820] = 52;
        ans[975] = 67;
        ans[950] = 64;
        ans[835] = 57;
        ans[808] = 58;
        ans[859] = 52;
        ans[920] = 54;
        ans[850] = 57;
        ans[771] = 44;
        ans[952] = 61;
        ans[847] = 53;
        ans[792] = 54;
        ans[753] = 53;
        ans[810] = 52;
        ans[786] = 52;
        ans[768] = 52;
        ans[782] = 43;
        ans[832] = 57;
        ans[977] = 65;
        ans[829] = 57;
        ans[827] = 55;
        ans[860] = 50;
        ans[904] = 65;
        ans[933] = 59;
        ans[929] = 63;
        ans[895] = 65;
        ans[922] = 59;
        ans[874] = 50;
        ans[843] = 54;
        ans[913] = 60;
        ans[711] = 48;
        ans[989] = 62;
        ans[983] = 62;
        ans[823] = 50;
        ans[848] = 60;
        ans[884] = 59;
        ans[791] = 46;
        ans[986] = 49;
        ans[744] = 50;
        ans[861] = 58;
        ans[842] = 54;
        ans[821] = 52;
        ans[939] = 59;
        ans[717] = 48;
        ans[757] = 54;
        ans[824] = 58;
        ans[878] = 49;
        ans[880] = 61;
        ans[761] = 56;
        ans[795] = 45;
        ans[781] = 56;
        ans[770] = 44;
        ans[873] = 60;
        ans[914] = 59;
        ans[741] = 51;
        ans[839] = 50;
        ans[831] = 47;
        ans[890] = 47;
        ans[819] = 51;
        ans[766] = 50;
        ans[811] = 52;
        ans[882] = 57;
        ans[803] = 51;
        ans[704] = 51;
        ans[962] = 56;
        ans[691] = 50;
        ans[687] = 39;
        ans[756] = 54;
        ans[918] = 59;
        ans[934] = 55;
        ans[951] = 55;
        ans[864] = 58;
        ans[818] = 55;
        ans[793] = 51;
        ans[785] = 56;
        ans[747] = 49;
        ans[857] = 48;
        ans[661] = 53;
        ans[790] = 54;
        ans[917] = 52;
        ans[690] = 39;
        ans[960] = 68;
        ans[789] = 47;
        ans[758] = 43;
        ans[907] = 61;
        ans[637] = 45;
        ans[865] = 59;
        ans[709] = 44;
        ans[817] = 61;
        ans[763] = 51;
        ans[825] = 50;
        ans[948] = 65;
        ans[870] = 53;
        ans[830] = 48;
        ans[760] = 60;
        ans[802] = 48;
        ans[775] = 55;
        ans[733] = 45;
        ans[923] = 56;
        ans[994] = 60;
        ans[879] = 57;
        ans[899] = 48;
        ans[930] = 59;
        ans[956] = 64;
        ans[916] = 56;
        ans[903] = 53;
        ans[969] = 56;
        ans[966] = 58;
        ans[974] = 59;
        ans[965] = 56;
        ans[995] = 60;
        ans[935] = 56;
        ans[973] = 65;
        ans[972] = 65;
        ans[877] = 61;
        ans[940] = 56;
        ans[932] = 63;
        ans[883] = 70;
        ans[901] = 61;
        ans[999] = 64;
        ans[938] = 59;
        ans[987] = 53;
        ans[902] = 54;
        ans[959] = 51;
        ans[925] = 58;
        ans[957] = 60;
        ans[993] = 59;
        ans[992] = 59;
        ans[906] = 57;
        ans[780] = 46;
        ans[944] = 58;
        ans[876] = 61;
        ans[996] = 61;
        ans[919] = 58;
        ans[909] = 58;
        ans[947] = 60;
        ans[968] = 65;
        ans[945] = 62;
        ans[745] = 58;
        ans[936] = 60;
        ans[875] = 55;
        ans[868] = 60;
        ans[953] = 66;
        ans[928] = 65;
        ans[984] = 62;
        ans[980] = 71;
        ans[997] = 67;
        ans[988] = 77;
        ans[982] = 65;
        ans[985] = 69;

        out.println(ans[in.nextInt()]);

        if (1 == 1) {
            return;
        }

        for (int n = 1; n <= 1000; n++) {
            while (a.get() > 10) {
                try {
                    Thread.sleep(50);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            new MyThread(n, a).start();
        }
    }
}

class MyThread extends Thread {
    AtomicInteger co;
    MyThread(int n, AtomicInteger a) {
        this.n = n;
        co = a;
    }

    int n;

    int ans = 0;
    int k   = 0;

    @Override
    public void run() {
        co.incrementAndGet();
        ans = 0;
        for (k = 4; k <= n; k += 2) {
            BigInteger dec = BigInteger.valueOf(n).pow(k);
            for (int d = k + 1; d <= k + 1; d++) {
                int old = ans;
                BigInteger a = dec.divide(BigInteger.valueOf(d));
                int[] digits = new int[k];
                for (int i = 0; i < k; i++) {
                    BigInteger[] q = a.divideAndRemainder(BigInteger.valueOf(n));
                    digits[i] = q[1].intValue();
                    a = q[0];
                }
                check(digits);
                digits[0] += 1;
                check(digits);
                digits[0] -= 2;
                check(digits);
            }
        }
        System.out.println("ans[" + n + "] = " + ans + ";");
        co.decrementAndGet();
    }

    void check(int[] a) {
        HashSet<Integer> diff = new HashSet<Integer>();
        for (int i : a) {
            diff.add(i);
        }
        if (diff.size() != k) {
            return;
        }

        int[] pairs = new int[n];
        Arrays.fill(pairs, -1);
        for (int m = 1; m <= k; m++) {
            int[] res = new int[k];
            int carry = 0;
            for (int i = 0; i < k; i++) {
                int t = a[i] * m + carry;
                if (t < 0) {
                    return;
                }
                res[i] = t % n;
                carry = t / n;
            }
            if (carry != 0) {
                return;
            }
            diff = new HashSet<Integer>();
            for (int i = 0; i < k; i++) {
                diff.add(res[i]);
                int x = res[i];
                int y = res[(i + 1) % k];
                if (pairs[x] == -1) {
                    pairs[x] = y;
                } else {
                    if (pairs[x] != y) {
                        return;
                    }
                }
            }
            if (diff.size() != k) {
                return;
            }
        }
        int q = 0;
        for (int i : pairs) {
            if (i != -1) {
                q++;
            }
        }
        if (q != k) {
            return;
        }
        ans++;
        //System.out.println(Arrays.toString(a));
    }

}