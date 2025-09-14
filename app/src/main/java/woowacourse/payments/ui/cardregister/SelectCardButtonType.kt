package woowacourse.payments.ui.cardregister

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import woowacourse.payments.R

enum class SelectCardButtonType(
    @DrawableRes val imageRes: Int,
    @StringRes val nameRes: Int,
) {
    BC(R.drawable.img_bc, R.string.bc_card),
    SHINHAN(R.drawable.img_shinhan, R.string.shinhan_card),
    KAKAO(R.drawable.img_kakao, R.string.kako_card),
    HYUNDAI(R.drawable.img_hyundai, R.string.hyundai_card),
    WOORI(R.drawable.img_woori, R.string.woori_card),
    LOTTE(R.drawable.img_lotte, R.string.lotte_card),
    HANA(R.drawable.img_hana, R.string.hana_card),
    KOOKMIN(R.drawable.img_kookmin, R.string.kookmin_card),
}
