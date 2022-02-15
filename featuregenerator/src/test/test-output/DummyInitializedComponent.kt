package com.americanexpress.android.intl.app.dagger.initialization

import com.americanexpress.android.acctsvcs.us.billing.MakePaymentModule
import com.americanexpress.android.acctsvcs.us.billing.MakePaymentServicesModule
import com.americanexpress.android.acctsvcs.us.dagger.PaymentsUsModule
import com.americanexpress.android.amexpay.dagger.AmexPayModule
import com.americanexpress.android.authn.common.AuthNModule
import com.americanexpress.android.autopay.AutopayModule
import com.americanexpress.android.benefits.BenefitsModule
import com.americanexpress.android.biometricsregistration.cache.BiometricsCacheModule
import com.americanexpress.android.cardactivation.CardActivationRefactorModule
import com.americanexpress.android.cardfreeze.CardFreezeModule
import com.americanexpress.android.checkspendingpower.dagger.CheckSpendingPowerModule
import com.americanexpress.android.creditlineincrease.CreditLineIncreaseModule
import com.americanexpress.android.dagger.PdfModule
import com.americanexpress.android.dagger.PlanItAutoPayModule
import com.americanexpress.android.dagger.SalutationModule
import com.americanexpress.android.defaultcard.dagger.DefaultCardModule
import com.americanexpress.android.dining.dagger.DiningModule
import com.americanexpress.android.directdebit.DirectDebitModule
import com.americanexpress.android.disputes.DisputesModule
import com.americanexpress.android.editemail.EditEmailModule
import com.americanexpress.android.editname.EditNameModule
import com.americanexpress.android.errorhandling.network.ErrorMapperModule
import com.americanexpress.android.external.IntentResolverModule
import com.americanexpress.android.featureintros.FeatureIntrosModule
import com.americanexpress.android.featureintros.smsreductionintro.SmsRedundancyModule
import com.americanexpress.android.featureintros.utils.storage.ExperimentalPrefsStorageModule
import com.americanexpress.android.financials.FinancialsModule
import com.americanexpress.android.flavordimension.dagger.DimensionSpecificModules
import com.americanexpress.android.help.FaqLegalModule
import com.americanexpress.android.intl.app.IntlApp
import com.americanexpress.android.intl.app.analytics.PznTrackingModule
import com.americanexpress.android.intl.app.configuration.FeatureConfigModule
import com.americanexpress.android.intl.app.dagger.CardActivationModule
import com.americanexpress.android.intl.app.dagger.CardEnrollmentModule
import com.americanexpress.android.intl.app.dagger.DigitalChargeVerificationModule
import com.americanexpress.android.intl.app.dagger.ExternalActionsModule
import com.americanexpress.android.intl.app.dagger.LoungeFinderModule
import com.americanexpress.android.intl.app.dagger.NotificationModule
import com.americanexpress.android.intl.app.dagger.PaymentFrameworkModule
import com.americanexpress.android.intl.app.dagger.PredictiveSearchModule
import com.americanexpress.android.intl.app.dagger.ServicingModule
import com.americanexpress.android.intl.app.dagger.SessionManagementModule
import com.americanexpress.android.intl.app.dagger.SignalRelayModule
import com.americanexpress.android.intl.app.feature.loungefinder.geofence.GeofenceRegistrationTask
import com.americanexpress.android.intl.app.feature.loungefinder.geofence.GeofenceResetEventPresenter
import com.americanexpress.android.intl.app.feature.loungefinder.geofence.GeofenceResetEventReceiver
import com.americanexpress.android.intl.app.feature.loungefinder.geofence.GeofenceTransitionPostInitialization
import com.americanexpress.android.intl.app.feature.loungefinder.geofence.LoungeFinderLocationUpdateIntentService
import com.americanexpress.android.intl.app.feature.loungefinder.geofence.LoungeFinderLocationUpdatePresenter
import com.americanexpress.android.intl.app.feature.servicing.di.ServicingCardModule
import com.americanexpress.android.intl.app.services.FCMListenerService
import com.americanexpress.android.intl.app.utils.InAuthInitializer
import com.americanexpress.android.intl.app.view.amexpay.HceLocalBroadcastReceiver
import com.americanexpress.android.intl.app.view.amexpay.issuernotificationflow.LCMSuccessPhase
import com.americanexpress.android.intl.app.view.amexpay.provisioningflow.ErrorLookupAlertDialog
import com.americanexpress.android.intl.app.view.fragment.ProgressDialogFragment
import com.americanexpress.android.languageselection.dagger.LocaleSelectionScreenModule
import com.americanexpress.android.localchampion.dagger.LocalChampionModule
import com.americanexpress.android.login.networking.dagger.LoginNetworkingModule
import com.americanexpress.android.loungefinder.dagger.LoungeFinderLocalModule
import com.americanexpress.android.loungefinder.service.MeldLoungeFinderModule
import com.americanexpress.android.maintab.model.MainTabCardModule
import com.americanexpress.android.membergetmember.MemberGetMemberModule
import com.americanexpress.android.membership.MembershipClientModule
import com.americanexpress.android.membership.MembershipModule
import com.americanexpress.android.membership.dagger.MembershipLinkModule
import com.americanexpress.android.membership.extrabenefit.ExtraPointsBenefitModule
import com.americanexpress.android.model.initialization.Initialization
import com.americanexpress.android.model.initialization.InitializationDataModule
import com.americanexpress.android.ndlfaq.NdlFaqModule
import com.americanexpress.android.offers.OffersModule
import com.americanexpress.android.onestream.OneStreamModule
import com.americanexpress.android.payments.PaymentsModule
import com.americanexpress.android.payments.networking.PaymentsNetworkingModule
import com.americanexpress.android.paymentsoptions.PaymentsOptionsModule
import com.americanexpress.android.payovertime.dagger.PayOverTimeModule
import com.americanexpress.android.payyourway.dagger.DeferredPaymentsModule
import com.americanexpress.android.pinmanagement.PinManagementModule
import com.americanexpress.android.pinmanagement.choose.refactor.dagger.ChoosePinModule
import com.americanexpress.android.profile.ProfileModule
import com.americanexpress.android.referafriend.dagger.ReferAFriendModule
import com.americanexpress.android.referafriendv2.dagger.ReferAFriendV2Module
import com.americanexpress.android.referafriendv2.dagger.ShareOptionCheckerModule
import com.americanexpress.android.sso.CustomTabsModule
import com.americanexpress.android.statements.StatementModule
import com.americanexpress.android.timelineclient.dagger.TimelineClientModule
import com.americanexpress.android.transactiondetails.TransactionDetailsModule
import com.americanexpress.android.transactionsearch.TransactionSearchModule
import com.americanexpress.android.userregistration.ui.UserRegistrationModule
import com.americanexpress.android.view.webview.AmexWebViewClient
import com.americanexpress.android.view.webview.WebViewModule
import com.americanexpress.biometricauth.dagger.CbisModule
import com.americanexpress.biometricauth.dagger.FingerprintModule
import com.americanexpress.pushnotifications.dagger.PushNotificationsModule
import com.americanexpress.supplementaryspendview.dagger.SupplementarySpendViewModule
import com.americanexpress.timeline.dagger.TimelineModule
import com.americanexpress.us.msl.autopay.MeldAutoPayModule
import com.americanexpress.us.msl.contactus.MeldContactUsModule
import com.americanexpress.us.msl.rewards.MissileRewardsModule
import com.americanexpress.us.msl.sso.SsoWebLinkModule
import dagger.BindsInstance
import dagger.Subcomponent
import io.aexp.otp.networking.OtpNetworkingModule
import io.aexp.payovertimev2.dagger.PayOverTimeV2Module
import io.aexp.peertopeer.PeerToPeerAndroidModule
import io.aexp.peertopeer.dagger.PeerToPeerModule
import io.aexp.peertopeer.networking.Peer2PeerNetworkingModule
import io.aexp.peertopeer.shared.ui.cache.PeerToPeerCacheModule
import io.aexp.planitV2.ui.viewplans.dagger.PlanItModule

@InitializedScope
@Subcomponent(
    modules = [
        AmexPayModule::class,
        AppCardModule::class,
        AuthNModule::class,
        AutopayModule::class,
        BenefitsModule::class,
        CardActivationModule::class,
        CardActivationRefactorModule::class,
        CardEnrollmentModule::class,
        CardFreezeModule::class,
        CardReplacementModule::class,
        CbisModule::class,
        CheckSpendingPowerModule::class,
        ChoosePinModule::class,
        CreditLineIncreaseModule::class,
        CustomTabsModule::class,
        BiometricsCacheModule::class,
        DefaultCardModule::class,
        DeferredPaymentsModule::class,
        DigitalChargeVerificationModule::class,
        DimensionSpecificModules::class,
        DiningModule::class,
        DirectDebitModule::class,
        DisputesModule::class,
        EditEmailModule::class,
        EditNameModule::class,
        ErrorMapperModule::class,
        ExperimentalPrefsStorageModule::class,
        ExternalActionsModule::class,
        ExtraPointsBenefitModule::class,
        FaqLegalModule::class,
        FeatureConfigModule::class,
        FeatureIntrosModule::class,
        FinancialsModule::class,
        FingerprintModule::class,
        InAuthModule::class,
        InitializationDataModule::class,
        LocalChampionModule::class,
        LoginNetworkingModule::class,
        LoungeFinderLocalModule::class,
        LoungeFinderModule::class,
        MainTabCardModule::class,
        MakePaymentModule::class,
        MakePaymentServicesModule::class,
        MeldAutoPayModule::class,
        MeldContactUsModule::class,
        MeldLoungeFinderModule::class,
        MemberGetMemberModule::class,
        MembershipClientModule::class,
        MembershipLinkModule::class,
        MembershipModule::class,
        MissileRewardsModule::class,
        NdlFaqModule::class,
        NotificationModule::class,
        OffersModule::class,
        OneStreamModule::class,
        OtpNetworkingModule::class,
        PaymentFrameworkModule::class,
        PaymentsModule::class,
        PaymentsNetworkingModule::class,
        PaymentsOptionsModule::class,
        PaymentsUsModule::class,
        PayOverTimeModule::class,
        PayOverTimeV2Module::class,
        PdfModule::class,
        Peer2PeerNetworkingModule::class,
        PeerToPeerAndroidModule::class,
        PeerToPeerCacheModule::class,
        io.aexp.peertopeer.v2.shared.ui.cache.PeerToPeerCacheModule::class,
        PeerToPeerModule::class,
        io.aexp.peertopeer.v2.dagger.PeerToPeerModule::class,
        PinManagementModule::class,
        PlanItAutoPayModule::class,
        PlanItModule::class,
        PredictiveSearchModule::class,
        ProfileModule::class,
        PushNotificationsModule::class,
        PznTrackingModule::class,
        ReferAFriendModule::class,
        ReferAFriendV2Module::class,
        SalutationModule::class,
        ServicingModule::class,
        ServicingCardModule::class,
        ShareOptionCheckerModule::class,
        SignalRelayModule::class,
        SmsRedundancyModule::class,
        SsoWebLinkModule::class,
        StatementModule::class,
        StatementsV2Module::class,
        SupplementarySpendViewModule::class,
        TimelineClientModule::class,
        TimelineModule::class,
        TransactionDetailsModule::class,
        ChoosePinModule::class,
        PaymentsOptionsModule::class,
        DeferredPaymentsModule::class,
        PznTrackingModule::class,
        CheckSpendingPowerModule::class,
        UserRegistrationModule::class,
        WebViewModule::class,
        TransactionSearchModule::class,
        LocaleSelectionScreenModule::class,
        IntentResolverModule::class,
        SessionManagementModule::class
    ]
)
@Suppress("ComplexInterface", "MethodOverloading")
interface DummyInitializedComponent : InitializedAndroidBindings {

    fun inject(app: IntlApp)

    // Services
    fun inject(service: GeofenceRegistrationTask)

    fun inject(servicePresenter: LoungeFinderLocationUpdatePresenter)

    fun inject(service: GeofenceTransitionPostInitialization)

    fun inject(receiverPresenter: GeofenceResetEventPresenter)

    fun inject(service: FCMListenerService)

    // Utilities and helper classes
    fun inject(inAuthInitializer: InAuthInitializer)

    fun inject(dialog: ErrorLookupAlertDialog)

    fun inject(hceLocalBroadcastReceiver: HceLocalBroadcastReceiver)

    fun inject(lcmSuccessPhase: LCMSuccessPhase)

    fun inject(amexWebViewClient: AmexWebViewClient)

    fun inject(loungeFinderLocationUpdateIntentService: LoungeFinderLocationUpdateIntentService)

    fun inject(receiver: GeofenceResetEventReceiver)

    // Fragments not inheriting from BaseFragment
    override fun inject(progressDialogFragment: ProgressDialogFragment)

    @Subcomponent.Builder
    interface Builder {
        fun inAuthModule(module: InAuthModule): Builder

        fun fingerprintModule(module: FingerprintModule): Builder

        fun cbisModule(module: CbisModule): Builder

        @BindsInstance
        fun initialization(initialization: Initialization): Builder

        fun build(): DummyInitializedComponent
    }
}
