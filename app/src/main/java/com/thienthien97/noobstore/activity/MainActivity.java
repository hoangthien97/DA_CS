package com.thienthien97.noobstore.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;
import com.thienthien97.noobstore.R;
import com.thienthien97.noobstore.adapter.LoaihangAdapter;
import com.thienthien97.noobstore.adapter.SanphamAdapter;
import com.thienthien97.noobstore.model.Loaihang;
import com.thienthien97.noobstore.model.Sanpham;
import com.thienthien97.noobstore.ultil.CheckConnect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    NavigationView navigationView;
    ListView listView;
    ArrayList<Loaihang> mangloaihang;
    LoaihangAdapter loaihangAdapter;

    int idloai =0;
    String tenloaihang="";
    String anhloaihang="";
    ArrayList<Sanpham> mangsanpham;
    SanphamAdapter sanphamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        if(CheckConnect.haveNetworkConnection(getApplicationContext())){
            ActionBar();
            ActionViewflipper();
            try {
                LayDuLieuLoai();
                LayDuLieuSPmoi();
                CatchOnItemListview();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            CheckConnect.ShowToast(getApplicationContext(),"Không có kết nối");
            finish();
        }
    }

    private void CatchOnItemListview() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                switch (i){
                    case 0:
                        if(CheckConnect.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            CheckConnect.ShowToast(getApplicationContext(),"Vui lòng kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(CheckConnect.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, ChuotActivity.class);
                            intent.putExtra("idloai",mangloaihang.get(i).getIdloai());
                            startActivity(intent);
                        }else{
                            CheckConnect.ShowToast(getApplicationContext(),"Vui lòng kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(CheckConnect.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, BanPhimActivity.class);
                            intent.putExtra("idloai",mangloaihang.get(i).getIdloai());
                            startActivity(intent);
                        }else{
                            CheckConnect.ShowToast(getApplicationContext(),"Vui lòng kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(CheckConnect.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, TaiNgheActivity.class);
                            intent.putExtra("idloai",mangloaihang.get(i).getIdloai());
                            startActivity(intent);
                        }else{
                            CheckConnect.ShowToast(getApplicationContext(),"Vui lòng kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(CheckConnect.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, LienHeActivity.class);
                            startActivity(intent);
                        }else{
                            CheckConnect.ShowToast(getApplicationContext(),"Vui lòng kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        if(CheckConnect.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, ThongTinActivity.class);
                            startActivity(intent);
                        }else{
                            CheckConnect.ShowToast(getApplicationContext(),"Vui lòng kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    //load dữ liệu sản phẩm mới vào trang chính
    private void LayDuLieuSPmoi() throws JSONException {
        //chuyen kieu du lieu tu JSON sang String de doc du lieu
        String data = "[\n" +
                "   {\n" +
                "      \"idsp\":\"15\",\n" +
                "      \"tensp\":\"Corsair VOID Pro Wireless RGB\",\n" +
                "      \"giasp\":\"3150000\",\n" +
                "      \"hinhsp\":\"https:\\/\\/buneqw.dm.files.1drv.com\\/y4mMwlkqyL3Jnv2radIS8eIZImIxE3MX_iXlqdKwpkPEe9hSc9eidgMJxQsQrTV9TuUD4MHn2AU8VLkJ6a0rshFRq4JKnm8m_uKEZ4cbB4fyMfUyWg6i2LHbz4J-Y8kA83RxIh6d6Cbu49oObFSsc92HNC3HHGKeaORfz2I7aNDnDbwX9EVtPYkU2QDZNsaK5g9xAvFpieLxEdl4iG5cUBKcQ?width=1800&height=1621&cropmode=none\",\n" +
                "      \"motasp\":\"Thi\\u1ebft K\\u1ebf: Over Ear (tr\\u00f9m k\\u00edn quanh tai)\\r\\nK\\u1ebft N\\u1ed1i: USB Receiver (wireless) for PC\\/ USB (charging) for PC.\\r\\nCh\\u1ee9c N\\u0103ng: Volume Control \\/ Microphone Mute \\/ Power Button \\/Led RGB \\/ 7.1 Dolby Digital \\/ Wireless\\r\\nTai nghe \\\"Gaming\\\" l\\u00e0 phi\\u00ean b\\u1ea3n kh\\u00f4ng d\\u00e2y c\\u1ee7a Void Pro v\\u1edbi t\\u1ea7n s\\u1ed1 kh\\u00f4ng d\\u00e2y 2.4GHz v\\u00e0 th\\u1eddi l\\u01b0\\u1ee3ng pin l\\u00ean \\u0111\\u1ebfn 16h ho\\u1ea1t \\u0111\\u1ed9ng.\",\n" +
                "      \"idloaisp\":\"3\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"idsp\":\"14\",\n" +
                "      \"tensp\":\"Razer Hammerhead PRO V2 \",\n" +
                "      \"giasp\":\"1890000\",\n" +
                "      \"hinhsp\":\"https:\\/\\/benlqw.dm.files.1drv.com\\/y4mLW1L-QujcT7lNY7r26zou6o1bDCeilOJHFyZ5yxkj3EsYO-SlbqSji2t6fs2OvOHdHLw0PicmbAPifx9jP7--pgJNCHkAGfcZApiyyPW3nh1m0DYv6iFpPfK8l86BaG8sphanPNFT9j5cJHfxE3iRMsYXmxc3oYzf3Oypf_EJ7LjaQg4ozZyOmghrpsuXqHKDrZLXaoDvIgK_3yopzN1Fg?width=1500&height=1000&cropmode=none\",\n" +
                "      \"motasp\":\"Razer HammerHead Pro v2 l\\u00e0 phi\\u00ean b\\u1ea3n ti\\u1ebfp theo c\\u1ee7a Razer HammerHead Pro - m\\u1ed9t trong nh\\u1eefng s\\u1ea3n ph\\u1ea9m n\\u1eb1m trong top tai nghe in-ear Gaming t\\u1ed1t nh\\u1ea5t. D\\u00f9 gi\\u00e1 kh\\u00f4ng \\u0111\\u1ed5i so v\\u1edbi phi\\u00ean b\\u1ea3n c\\u0169 - 1.889.000 VN\\u0110 cho b\\u1ea3n Pro c\\u00f3 micro v\\u00e0 1.329.000 VN\\u0110 cho b\\u1ea3n th\\u01b0\\u1eddng kh\\u00f4ng micro - Razer HammerHead Pro v2 l\\u1ea1i mang tr\\u00ean m\\u00ecnh nhi\\u1ec1u c\\u1ea3i ti\\u1ebfn h\\u1ea5p d\\u1eabn.\\r\\nCh\\u1ea5t \\u00e2m \\u0111\\u1eb7c tr\\u01b0ng c\\u1ee7a d\\u00f2ng HammerHead v\\u1eabn \\u0111\\u01b0\\u1ee3c gi\\u1eef nguy\\u00ean, \\u0111\\u00f3 l\\u00e0 d\\u1ea3i bass \\u0111\\u1ea7y \\u0111\\u1ee7 c\\u1ea3 ch\\u1ea5t v\\u00e0 l\\u01b0\\u1ee3ng. \\u0110\\u1ed1i v\\u1edbi c\\u00e1c fan c\\u1ee7a th\\u1ec3 lo\\u1ea1i nh\\u1ea1c EDM, Pop v\\u00e0 Hip-hop, ch\\u1eafc ch\\u1eafn HammerHead Pro v2 s\\u1ebd th\\u1ecfa m\\u00e3n \\u0111\\u00f4i tai c\\u1ee7a b\\u1ea1n.\",\n" +
                "      \"idloaisp\":\"3\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"idsp\":\"13\",\n" +
                "      \"tensp\":\"\\r\\nG.Skill RIPJAWS KM570 MX \",\n" +
                "      \"giasp\":\"2090000\",\n" +
                "      \"hinhsp\":\"https:\\/\\/benmqw.dm.files.1drv.com\\/y4msAbcMA8GpL6FxrMDWRhqqfyfGbqw53V6zOOzWoFUinNxJyGlORYnjtUq5I9fsN6ulmg8V97T5pV4mlADpDURgu5RFSh_gWpf5qhlcj5DJ3mdsQRe20lwUiDVsmZW2jPhHjZVnsR-C2aLjBUCHZd_x5hp-pllWtJ67Uty8v6uBGfZZeuVcJNCrr7WOujCDNrwsnGIKzTPidSlLbVitpPWNA?width=2048&height=1365&cropmode=none\",\n" +
                "      \"motasp\":\"B\\u00e0n ph\\u00edm LED KM570 MX v\\u1edbi thi\\u1ebft k\\u1ebf led \\u0111\\u01a1n s\\u1eafc \\u0111\\u01b0\\u1ee3c cho l\\u00e0 c\\u00f3 \\u0111\\u1ed9 b\\u1ec1n v\\u00e0 d\\u1ec5 d\\u00e0ng s\\u1eed d\\u1ee5ng v\\u1edbi ng\\u01b0\\u1eddi d\\u00f9ng c\\u01a1 b\\u1ea3n cho \\u0111\\u1ebfn ng\\u01b0\\u1eddi ch\\u01a1i game hay l\\u00e0m vi\\u1ec7c. \\u0111\\u01b0\\u1ee3c s\\u1eed d\\u1ee5ng Cherry MX switch c\\u1ee7a \\u0110\\u1ee9c, c\\u00f3 2 ki\\u1ec3u switch cho ng\\u01b0\\u1eddi d\\u00f9ng l\\u1ef1a ch\\u1ecdn g\\u1ed3m Red, Brow, Blue v\\u1edbi \\u0111\\u1ed9 b\\u1ec1n l\\u00ean t\\u1edbi 50 tri\\u1ec7u l\\u01b0\\u1ee3t b\\u1ea5m. B\\u00e0n ph\\u00edm Gskill kh\\u00f4ng c\\u00f3 ph\\u1ea7n m\\u1ec1m hi\\u1ec7u ch\\u1ec9nh thi\\u1ebft l\\u1eadp, m\\u00e0 n\\u00f3 thi\\u1ebft l\\u1eadp ngay tr\\u00ean b\\u00e0n ph\\u00edm nh\\u01b0 hi\\u1ec7u ch\\u1ec9nh led v\\u00e0 ph\\u00edm n\\u00f3ng.\\r\\nKM570 MX l\\u00e0 b\\u00e1n ph\\u00edm l\\u00fd t\\u01b0\\u1edfng cho ng\\u01b0\\u1eddi d\\u00f9ng \\u0111ang t\\u00ecm ki\\u1ebfm m\\u1ed9t b\\u00e0n ph\\u00edm c\\u01a1 v\\u1edbi r\\u1ea5t nhi\\u1ec1u t\\u00ednh n\\u0103ng trong t\\u1ea7m gi\\u00e1 2tr, khi m\\u00e0 n\\u00f3 h\\u1ed9i t\\u1ee5 nh\\u1eefng th\\u00f4ng s\\u1ed1 k\\u1ef9 thu\\u1eadt nh\\u01b0. C\\u00f4ng ngh\\u1ec7 ch\\u1ed1ng N-key, t\\u1ea7n s\\u1ed1 qu\\u00e9t si\\u00eau nh\\u1eady 1000Hz.\",\n" +
                "      \"idloaisp\":\"2\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"idsp\":\"12\",\n" +
                "      \"tensp\":\"B\\u00e0n ph\\u00edm c\\u01a1 IKBC CD87 - PBT\",\n" +
                "      \"giasp\":\"1640000\",\n" +
                "      \"hinhsp\":\"https:\\/\\/benjqw.dm.files.1drv.com\\/y4mu6EsenW4AQK9ynDmlSoD0zzYRp-WhJTvCAqCQEKEPCPSdji7tqctBxHkycxpLYb3riO6RA6Zmc0MJcCs7bRaqlCxjpUx45WViWS68d3wgY6B6lB1xRe2UhYCr8hHPN-pbi_hRArx9HeVHBsos50OYXlQViXbV52nvXOEMvAnaFknJXLtTEGeLTiVvUeKj9hza1_5-3OTc2v8o6VyWpLu6Q?width=783&height=524&cropmode=none\",\n" +
                "      \"motasp\":\"S\\u1eed d\\u1ee5ng ph\\u00edm c\\u01a1 h\\u1ecdc Cherry Mx. H\\u00e3ng Ikbc gi\\u00fap cho ng\\u01b0\\u1eddi d\\u00f9ng 1 tr\\u1ea3i nghi\\u1ec7m ph\\u00edm b\\u1ea5t c\\u1ef1c t\\u1ed1t tr\\u00ean \\u0111\\u1ea7u ng\\u00f3n tay c\\u1ee7a m\\u00ecnh. Ngo\\u00e0i ra c\\u00e1c ph\\u00edm b\\u1ea5m \\u0111\\u01b0\\u1ee3c s\\u1eed d\\u1ee5ng lo\\u1ea1i keycap PBT cao c\\u1ea5p. Kh\\u00f4ng g\\u00e2y m\\u1ecfi tay hay hi\\u1ec7n t\\u01b0\\u1ee3ng b\\u00e1m v\\u00e2n tay l\\u00ean ph\\u00edm.\\r\\nV\\u1edbi v\\u1ecf b\\u00e0n ph\\u00edm c\\u1ef1c ch\\u1eafc ch\\u1eafn nh\\u01b0ng kh\\u00f4ng g\\u00e2y n\\u1eb7ng n\\u1ec1. CD87 c\\u00f3 th\\u1ec3 g\\u1ea7n nh\\u01b0 so s\\u00e1nh v\\u1edbi c\\u00e1c lo\\u1ea1i b\\u00e0n ph\\u00edm c\\u01a1 ch\\u1ea5t l\\u01b0\\u1ee3ng c\\u1ee7a Filco hay Leopold.\",\n" +
                "      \"idloaisp\":\"2\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"idsp\":\"11\",\n" +
                "      \"tensp\":\"Chu\\u1ed9t Fuhlen CO 300S\",\n" +
                "      \"giasp\":\"349000\",\n" +
                "      \"hinhsp\":\"https:\\/\\/benhqw.dm.files.1drv.com\\/y4mBUu6RKwkbSMKvJIo50NTRsXNkwtLmzMvz_kCCGDOPb_0-ICepbufQ0X2g9ATRMhR4WIuXYQ-KQ0YnJPH8TjBvnlT4LePJtnkvtlE5CQRGUjrQiufiv1rF80hLPIFd3kbk0snZJTdxfNjW2uTKBfE9MIRydTY9OL4dpqCx7sjNmrQ7teAks8n1kABx5tbvOF1fU3_rD3IfQhif5pFuQQQ5Q?width=1000&height=1000&cropmode=none\",\n" +
                "      \"motasp\":\"V\\u1edbi v\\u1ecf ngo\\u00e0i c\\u1ee9ng c\\u00e1p, \\u0111\\u01a1n gi\\u1ea3n v\\u1edbi thi\\u1ebft k\\u1ebf c\\u00f4ng th\\u00e1i h\\u1ecdc Ergonomic. S\\u1edf h\\u1eefu m\\u00e0u \\u0111en n\\u1ed5i b\\u1eadt c\\u00f9ng v\\u1edbi c\\u00e1c \\u0111\\u01b0\\u1eddng vi\\u1ec1n chu\\u1ea9n n\\u00e9t. S\\u1ea3n ph\\u1ea9m mang d\\u00e1ng v\\u1ebb thanh tao, s\\u1eed d\\u1ee5ng s\\u1ebd r\\u1ea5t nh\\u1eb9 nh\\u00e0ng v\\u00e0 \\u00eam \\u00e1i trong th\\u1eddi gian d\\u00e0i.\\r\\n\\u0110\\u01b0\\u1ee3c s\\u1eed d\\u1ee5ng n\\u00fat b\\u1ea5m Omron cao c\\u1ea5p v\\u1edbi tu\\u1ed5i th\\u1ecd l\\u00ean t\\u1edbi h\\u01a1n 20 tri\\u1ec7u l\\u1ea7n nh\\u1ea5n. K\\u00e8m theo ch\\u1ebf \\u0111\\u1ed9 c\\u1ea3m bi\\u1ebfn quang h\\u1ecdc 4 m\\u1ee9c t\\u00f9y ch\\u1ec9nh t\\u1eeb 1000\\/1500\\/2500\\/3500 DPI. Game th\\u1ee7 c\\u00f3 th\\u1ec3 t\\u1ef1 do s\\u1eed d\\u1ee5ng tr\\u00ean m\\u1ecdi \\u0111\\u1ea5u tr\\u01b0\\u1eddng.\\r\\n\",\n" +
                "      \"idloaisp\":\"1\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"idsp\":\"10\",\n" +
                "      \"tensp\":\"Chu\\u1ed9t SteelSeries Rival 310\",\n" +
                "      \"giasp\":\"1390000\",\n" +
                "      \"hinhsp\":\"https:\\/\\/benkqw.dm.files.1drv.com\\/y4mr6FIwdIcaD3PSibQcV5gfka6hgsdlVA0KTKkHmxkFbWvcoVC2DJYynWK6TGwqKgZlYPT33voaDzGUjPP5T5vOMiKy-lmsW9fQQZ9jbgL1Y0GIV0v2DgHJVEjOyOuFsQZ7Aj6UIANCEWrN5x56tJK_SoDX0UC6c_fu8czcFZGluR69VGASOZ0dig7ybDPfp5KTn95l0WIPmo-bjgbmnT0NA?width=1500&height=1500&cropmode=none\",\n" +
                "      \"motasp\":\"TrueMove3 c\\u00f3 \\u0111\\u1ed9 nh\\u1ea1y l\\u00ean t\\u1edbi 12000 CPI, 350 IPS \\u0111\\u01b0\\u1ee3c th\\u1eeba h\\u01b0\\u1edfng nhi\\u1ec1u tinh t\\u00fay t\\u1eeb nh\\u00e0 s\\u1ea3n xu\\u1ea5t c\\u1ea3m bi\\u1ebfn h\\u00e0ng \\u0111\\u1ea7u Th\\u1ebf Gi\\u1edbi PixArt. Thay v\\u00ec ch\\u1ec9 t\\u1eadp trung v\\u00e0o \\u0111\\u1ed9 nh\\u1ea1y CPI, c\\u1ea3m bi\\u1ebfn TrueMove3 c\\u00f3 kh\\u1ea3 n\\u0103ng tracking 1:1 c\\u1ef1c k\\u00ec ch\\u00ednh x\\u00e1c.\\r\\nRival 310 l\\u00e0 1 \\u0111\\u1eb7c tr\\u01b0ng cho d\\u00f2ng chu\\u1ed9t chuy\\u00ean game, n\\u00f3 c\\u00f3 tr\\u1ecdng l\\u01b0\\u1ee3ng nh\\u1eb9 \\u0111\\u00e1ng kinh ng\\u1ea1c ch\\u1ec9 \\u1edf m\\u1ee9c 88,3 gram. M\\u1ecdi b\\u1ed9 ph\\u1eadn c\\u1ea5u t\\u1ea1o n\\u00ean Rival 310 \\u0111\\u1ec1u \\u0111\\u01b0\\u1ee3c gia c\\u00f4ng c\\u1ef1c k\\u00ec c\\u1ea9n th\\u1eadn v\\u00e0 t\\u1ec9 m\\u1ec9, \\u0111em l\\u1ea1i b\\u1ed9 b\\u1ec1n tuy\\u1ec7t v\\u1eddi.\",\n" +
                "      \"idloaisp\":\"1\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"idsp\":\"9\",\n" +
                "      \"tensp\":\"Logitech G633\",\n" +
                "      \"giasp\":\"2990000\",\n" +
                "      \"hinhsp\":\"https:\\/\\/beniqw.dm.files.1drv.com\\/y4mgOX_oz8tJDh5PuyQ8qWdoJGljLau0rSQBM4fhz5VWegmeZGTpq-RKmgFXzzZy4dpDsloyTDt3_3C8qYXDFORsTgCAlNB5ztFU41j7JwmLFgaER4vLvRVdr13fFy0OJn2c04Dmex0iXzWmEbHp1O3AEEu4Z9BbBr3gqMDc8Zy040O8T3ABj2bFoBvBksqxpe8aK1tqLZW6P2-rdeturaGnQ?width=1000&height=1000&cropmode=none\",\n" +
                "      \"motasp\":\"G633 c\\u00f3 thi\\u1ebft k\\u1ebf r\\u1ea5t g\\u00e2y \\u1ea5n t\\u01b0\\u1ee3ng khi m\\u1edbi nh\\u00ecn v\\u00e0o, ki\\u1ec3u thi\\u1ebft k\\u1ebf mang ch\\u00fat h\\u01a1i h\\u01b0\\u1edbng t\\u01b0\\u01a1ng lai, gi\\u1ed1ng nh\\u01b0 m\\u1ed9t chi\\u1ebfc tai nghe \\u0111\\u01b0\\u1ee3c l\\u1ea5y ra t\\u1eeb m\\u1ed9t b\\u1ed9 phim vi\\u1ec5n t\\u01b0\\u1edfng. Ngay khi c\\u1ea7m tr\\u00ean tay, G633 cho m\\u1ed9t c\\u1ea3m gi\\u00e1c r\\u1ea5t cao c\\u1ea5p, t\\u1eeb vi\\u1ec7c ch\\u1ecdn ch\\u1ea5t li\\u1ec7u cho \\u0111\\u1ebfn ch\\u1ea5t l\\u01b0\\u1ee3ng gia c\\u00f4ng \\u0111\\u1ec1u kh\\u00f4ng th\\u1ec3 ch\\u00ea v\\u00e0o \\u0111\\u00e2u \\u0111\\u01b0\\u1ee3c. Ph\\u1ee5 ki\\u1ec7n c\\u1ee7a G633 kh\\u00e1 \\u0111\\u01a1n gi\\u1ea3n nh\\u01b0ng nh\\u01b0 v\\u1eady l\\u00e0 \\u0111\\u1ee7 v\\u1edbi m\\u1ed9t chi\\u1ebfc tai nghe ch\\u01a1i game.\\r\\nS\\u1ee3i d\\u00e2y v\\u1edbi jack 3.5mm c\\u1ee7a G633 kh\\u00f4ng c\\u00f3 g\\u00ec \\u0111\\u1ec3 nh\\u1eafc \\u0111\\u1ebfn nhi\\u1ec1u. Ph\\u1ee5 ki\\u1ec7n n\\u00e0y c\\u00f3 ch\\u1ea5t l\\u01b0\\u1ee3ng t\\u1ed1t, kh\\u00e1 m\\u1ec1m, v\\u1edbi 2 \\u0111\\u1ea7u \\u0111\\u1ec1u l\\u00e0 jack 3.5mm gi\\u00fap ch\\u00fang ta s\\u1eed d\\u1ee5ng \\u0111\\u01b0\\u1ee3c c\\u1ea3 mic c\\u1ee7a tai nghe khi c\\u1eafm v\\u00e0o c\\u00e1c thi\\u1ebft b\\u1ecb nh\\u01b0 \\u0111i\\u1ec7n tho\\u1ea1i, laptop, m\\u00e1y t\\u00ednh b\\u1ea3ng.\",\n" +
                "      \"idloaisp\":\"3\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"idsp\":\"8\",\n" +
                "      \"tensp\":\"HyperX Cloud II Gunmental\",\n" +
                "      \"giasp\":\"2490000\",\n" +
                "      \"hinhsp\":\"https:\\/\\/bengqw.dm.files.1drv.com\\/y4mNtb97lXTkSuGOhfd3mTuiSQiAuE03FGsyWy1Bk3WyTbCa63qcysz9QxPtW0h0Iev9ah3VkTz4SGxfO86u8rB61MYNrUX9brCgsS_7vcKXes9WlGVt-I13ZSws5oLPaVD2LkD41OIo026FhbGyCa3O0uRGJ8wyFFRl0sMRTPb3-CpX-eW2SHH5RmhMrvSkz6BQj8ijtBluOAha4DgY1k9Zg?width=1080&height=1080&cropmode=none\",\n" +
                "      \"motasp\":\"Kingston HyperX Cloud II, th\\u1ebf h\\u1ec7 m\\u1edbi nh\\u1ea5t hi\\u1ec7n nay trong series tai nghe Cloud c\\u1ee7a Kingston v\\u00e0 l\\u00e0 m\\u1eabu tai nghe \\u0111\\u01b0\\u1ee3c nhi\\u1ec1u ng\\u01b0\\u1eddi cho r\\u1eb1ng s\\u1ebd l\\u00e0 m\\u1ed9t \\u0111\\u1ed1i tr\\u1ecdng x\\u1ee9ng \\u0111\\u00e1ng v\\u1edbi hai m\\u1eabu tai nghe gaming ph\\u1ed5 bi\\u1ebfn l\\u00e0 Razer Kraken v\\u00e0 Steelseries Siberia. So v\\u1edbi ng\\u01b0\\u1eddi \\u0111\\u00e0n em phi\\u00ean b\\u1ea3n \\u0111\\u1ea7u c\\u1ee7a m\\u00ecnh th\\u00ec Cloud II c\\u00f3 th\\u00eam cho m\\u00ecnh soundcard 7.1, ch\\u1ea5t \\u00e2m \\u0111\\u01b0\\u1ee3c c\\u1ea3i thi\\u1ec7n h\\u01a1n c\\u0169ng nh\\u01b0 nhi\\u1ec1u m\\u00e0u s\\u1eafc h\\u01a1n cho c\\u00e1c game th\\u1ee7 l\\u1ef1a ch\\u1ecdn.\\r\\nV\\u1edbi thi\\u1ebft k\\u1ebf khung nh\\u00f4m v\\u00e0 c\\u00e1c \\u1ed1p \\u0111\\u1ec7m b\\u1eb1ng da kh\\u00f4ng ch\\u1ec9 \\u0111em l\\u1ea1i c\\u1ea3m gi\\u00e1c c\\u1ee9ng c\\u00e1p m\\u00e0 l\\u1ea1i v\\u00f4 c\\u00f9ng sang tr\\u1ecdng cho s\\u1ea3n ph\\u1ea9m n\\u00e0y. V\\u00e0 m\\u1ed9t \\u0111i\\u1ec3m c\\u1ed9ng n\\u1eefa l\\u00e0 vi\\u1ec7c microphone c\\u00f3 th\\u1ec3 th\\u00e1o r\\u1eddi tr\\u00e1nh v\\u01b0\\u1edbng v\\u00edu khi kh\\u00f4ng ph\\u1ea3i s\\u1eed d\\u1ee5ng \\u0111\\u1ebfn mic.\",\n" +
                "      \"idloaisp\":\"3\"\n" +
                "   }\n" +
                "]";
        JSONArray jsonArr = new JSONArray(data);
        int ID=0;
        String Tensanpham = "";
        int Giasanpham = 0;
        String Hinhsanpham = "";
        String Motasanpham  = "";
        int IDLoai = 0;
        for (int i = 0; i < jsonArr.length(); i++) {
            try {
                JSONObject jsonObject = jsonArr.getJSONObject(i);
                ID = jsonObject.getInt("idsp");
                Tensanpham = jsonObject.getString("tensp");
                Giasanpham = jsonObject.getInt("giasp");
                Hinhsanpham = jsonObject.getString("hinhsp");
                Motasanpham = jsonObject.getString("motasp");
                IDLoai  = jsonObject.getInt("idloaisp");
                mangsanpham.add(new Sanpham(ID,Tensanpham,Giasanpham,Hinhsanpham,Motasanpham,IDLoai));
                sanphamAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    //load du lieu vao thanh menu
    private void LayDuLieuLoai() throws JSONException{
        //chuyen kieu du lieu tu JSON sang String de doc du lieu
        String data = "[{\"idloai\":\"1\",\"tenloaihang\":\"Chu\\u1ed9t\",\"anhloaihang\":\"https:\\/\\/aknlqw.dm.files.1drv.com\\/y4m1p1-UhkXC9uSIxAEeE9IFSBvqTihEbitb0IAIETrkxNlD9-h1YKQmUfbrSP_ZuRnEzl3CP2cqz5R26vaO1Af545x87hnZJqHQ982T_zL8VXuDpLMZW4p5SOzzykAXdYc4d4zpbvX1xXtgMUZr0Z-A4hH3-s-OZeGKPf4abKzCnUlspxRvpuYoUfRpbCoRey7rUihxRZmGmwR7wPZTIZtbA?width=521&height=342&cropmode=none\"}," +
                "{\"idloai\":\"2\",\"tenloaihang\":\"B\\u00e0n ph\\u00edm\",\"anhloaihang\":\"https:\\/\\/a0ndqw.dm.files.1drv.com\\/y4mYQnjPm_zusH3ob5y8E0Y4kQb-3qqexKwle_48FDIIf8XodeAL17QOqZPGFrVDBIXDXqv4TgTwA4qNE5Y-9CcFMfAeB2w6cB0vfkITtkBPMqLw3wr-PGuF7i5MuTzk9TPoTwg_wyY3uNgzNazy0UGyPNnOvp2iJMBCkdrKcOYOX5YeSJDSJkr-hwThFHKH_iGYSvvawGLyhwSjRSR1Z1mzg?width=937&height=401&cropmode=none\"}," +
                "{\"idloai\":\"3\",\"tenloaihang\":\"Tai nghe\",\"anhloaihang\":\"https:\\/\\/a0neqw.dm.files.1drv.com\\/y4mzcQuinqDKs0hAo-xXLnab5GlNk3KdsRes84izANxTs6Hl5L6cV96QWfpn2n9kjKkkHCURhUTKDl1yiEJmVuRpgXswQiahaoPi-qSsH_Vg7nSVF5rWo_BV7RKKW2PKV0vQI9YE-IZNk8uZbyPRh4At9n0oV_xt-eY5u92WHHOw8i5PXMFnNyHosPIZhX46WbPme4NzNs19rPBm-j3lBR86Q?width=960&height=552&cropmode=none\"}]";
        JSONArray jsonArr = new JSONArray(data);
        for(int i=0; i<jsonArr.length();i++){
            JSONObject jsonObject = jsonArr.getJSONObject(i);
            idloai = jsonObject.getInt("idloai");
            tenloaihang = jsonObject.getString("tenloaihang");
            anhloaihang = jsonObject.getString("anhloaihang");
            mangloaihang.add(new Loaihang(idloai,tenloaihang,anhloaihang));
            loaihangAdapter.notifyDataSetChanged();
        }
        mangloaihang.add(4, new Loaihang(0,"Liên hệ","https://cdn2.iconfinder.com/data/icons/font-awesome/1792/phone-128.png"));
        mangloaihang.add(5, new Loaihang(0,"Thông tin","https://cdn4.iconfinder.com/data/icons/eldorado-mobile/40/info_3-512.png"));
        mangloaihang.add(6, new Loaihang(0, "Báo cáo","https://cdn3.iconfinder.com/data/icons/google-material-design-icons/48/ic_report_48px-128.png"));
    }
   /* private void LayDuLieuLoai() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(ServerN.LinkLoaihang,  new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    for(int i=0; i<response.length();i++){
                        try {
                           JSONObject jsonObject = response.getJSONObject(i);
                            idloai = jsonObject.getInt("idloai");
                            tenloaihang = jsonObject.getString("tenloaihang");
                            anhloaihang = jsonObject.getString("anhloaihang");
                            mangloaihang.add(new Loaihang(idloai,tenloaihang,anhloaihang));
                            loaihangAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mangloaihang.add(4, new Loaihang(0,"Liên hệ","https://www.freeiconspng.com/uploads/android-phone--android-r2--128px--icon-gallery-0.png"));
                    mangloaihang.add(5, new Loaihang(0,"Thông tin","https://cdn4.iconfinder.com/data/icons/eldorado-mobile/40/info_3-512.png"));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnect.ShowToast(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }*/
   /*    private void LayDuLieuSPmoi() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(ServerN.LinkSPmoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null){
                    int ID=0;
                    String Tensanpham = "";
                    int Giasanpham = 0;
                    String Hinhsanpham = "";
                    String Motasanpham  = "";
                    int IDLoai = 0;
                    for(int i=0; i<response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("idsp");
                            Tensanpham = jsonObject.getString("tensp");
                            Giasanpham = jsonObject.getInt("giasp");
                            Hinhsanpham = jsonObject.getString("hinhsp");
                            Motasanpham = jsonObject.getString("motasp");
                            IDLoai  = jsonObject.getInt("idloaisp");
                             mangsanpham.add(new Sanpham(ID,Tensanpham,Giasanpham,Hinhsanpham,Motasanpham,IDLoai));
                            sanphamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }*/

    //Tao slide quang cao
    private void ActionViewflipper() {
        ArrayList<String> ads = new ArrayList<>();
        ads.add("https://a0nmqw.dm.files.1drv.com/y4mgl9p_OnojEcyVHQJvREkVGbGrIqhWkNvBMkRKOUrpcyqklXAl9mYtVjtaiPjH2H9HsGstTcaAOSOfsBVeRHqOJigvUf2DTRpNJOl25xqzkdK0AeOv4Lpulgsz_vMXmXPXJYDtqWyfWunjswDsYsczMzfZ_qwrfAJCMtmUkc9mICMU7zsm1S9YPUSou1prwOneaBzyn2tmFyOjXxSK52qZQ?width=1920&height=600&cropmode=none");
        ads.add("https://a0njqw.dm.files.1drv.com/y4mQ2d-5yxSkHJz1NOWUN_vpntC9Hg44ilMbLXpOcWd9dVywl_bKSNCPMyUHPHatJFwE0y7b_aw_MDvo-KpbQcX2X7E1Jl441Kuwl53vQZ9vT-pMltbdJFtnP-2TD73PWyDfxMLhZG63F5zyeLP33i2hV3EpRXObhSw4bnfp5sgDvhAQ_ulVzDT_NnlD4v1ffR7g-NWugcI3WeXuxxvbFMp0A?width=1920&height=1030&cropmode=none");
        ads.add("https://a0nkqw.dm.files.1drv.com/y4mdFJnOBgXE5TCCNzoc345ws-oTqW3qQ9e6u40cvZ0I7QDtFLyFBGvY2-I878RJJJ1em-EpTdIOb2bHbKaJ2n5YfeO4V-IpoDiNqmNPb4K6yTMnghjQ8D7cM39djT0wdgzcDsYYZpiEK1f77pUv-XNpKFpYH6nDpNpyKn8FxxG-tjVBuY4RzVa-eHtHzdsUbmsg6Sczh6iTaj4t_xj_B263Q?width=1920&height=1030&cropmode=none");
        ads.add("https://a0nhqw.dm.files.1drv.com/y4mj4Wh1QZenxqleSQqhmevAhwDuOuOdslZFY-51UW6lYLr3YyiCu8wkCWtxSeZEV6gJWiqZER7TfI8gkQDikoOVw--asdu4deUZPeC5KjgqnPbHHuH7ZFI90kfdMwMxiXRePHQtuskjLDJkBZWhmbF98c7YkoFgmV4i2n75ReAKjO5y3l7koLvm1Ekv9S1B6ZjmhrbSTTAh__Ugp2SpXWY5g?width=1920&height=600&cropmode=none");
       /* qc.add("https://www.playzone.vn/image/catalog/san%20pham/ozone/keyboard/banner%20Logitech.jpg");
        qc.add("https://www.playzone.vn/image/catalog/Banner/28942964_2089110457771589_1360206067_o.jpg");
        qc.add("https://www.playzone.vn/image/catalog/Banner/banner%20to%201920_500/OZONE%20NEON%20M50.jpg");
        qc.add("https://www.playzone.vn/image/catalog/Banner/banner%20to%201920_500/ANTEC%20ADVANCE.jpg");*/
        for(int i=0; i<ads.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(ads.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        //Set time cho quang cao chay
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation anim_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_r);
        Animation anim_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_r);
        viewFlipper.setInAnimation(anim_slide_in);
        viewFlipper.setOutAnimation(anim_slide_out);
    }

    //hien thi thanh menu
    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void Anhxa(){
        toolbar = (Toolbar) findViewById(R.id.toolbarmainscreen);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        navigationView = (NavigationView) findViewById(R.id.navigationview);
        listView = (ListView) findViewById(R.id.lstviewmainscreen);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mangloaihang = new ArrayList<>();
        mangloaihang.add(0,new Loaihang(0,"Trang chủ","https://cdn2.iconfinder.com/data/icons/font-awesome/1792/home-128.png"));
        loaihangAdapter = new LoaihangAdapter(mangloaihang,getApplicationContext());
        listView.setAdapter(loaihangAdapter);
        mangsanpham = new ArrayList<>();
        sanphamAdapter = new SanphamAdapter(getApplicationContext(),mangsanpham);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setAdapter(sanphamAdapter);
    }
}
